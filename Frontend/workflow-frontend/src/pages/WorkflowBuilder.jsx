import { useState } from "react";
import axios from "axios";
import Layout from "../components/Layout";

const WORKFLOWS = {
  LEAVE: {
    name: "Leave Approval",
    steps: [
      { name: "Apply Leave", type: "TASK" },
      { name: "Manager Approval", type: "APPROVAL" },
      { name: "HR Approval", type: "APPROVAL" }
    ],
    rules: [
      { from: 0, to: 1, condition: "days <= 5" },
      { from: 0, to: 2, condition: "days > 5" }
    ]
  },

  LOAN: {
    name: "Bank Loan Approval",
    steps: [
      { name: "Apply Loan", type: "TASK" },
      { name: "Manager Review", type: "APPROVAL" },
      { name: "Finance Approval", type: "APPROVAL" }
    ],
    rules: [
      { from: 0, to: 1, condition: "amount < 50000" },
      { from: 0, to: 2, condition: "amount >= 50000" }
    ]
  }
};

const WorkflowBuilder = () => {

  const token = localStorage.getItem("token");

  const [selected, setSelected] = useState("");
  const [config, setConfig] = useState(null);

  const handleChange = (value) => {
    setSelected(value);
    setConfig(WORKFLOWS[value]);
  };

  const createWorkflow = async () => {

    if (!config) {
      alert("Select workflow");
      return;
    }

    try {

      const stepIdMap = {};

      // CREATE STEPS
      for (let i = 0; i < config.steps.length; i++) {

        const step = config.steps[i];

        const res = await axios.post(
          "http://localhost:8080/steps",
          {
            name: step.name,
            stepType: step.type,
            stepOrder: i
          },
          {
            headers: { Authorization: `Bearer ${token}` }
          }
        );

        stepIdMap[i] = res.data.id;
      }

      // CREATE WORKFLOW
      const workflowRes = await axios.post(
        "http://localhost:8080/workflows",
        {
          name: config.name,
          version: 1,
          isActive: true,
          startStepId: stepIdMap[0]
        },
        {
          headers: { Authorization: `Bearer ${token}` }
        }
      );

      const workflowId = workflowRes.data.id;

      // ATTACH STEPS
      for (let i = 0; i < config.steps.length; i++) {

        await axios.post(
          `http://localhost:8080/steps/${stepIdMap[i]}/step`,
          { workflowId },
          {
            headers: { Authorization: `Bearer ${token}` }
          }
        );
      }

      // CREATE RULES
      for (let rule of config.rules) {

        await axios.post(
          `http://localhost:8080/rules/${stepIdMap[rule.from]}/rule`,
          {
            condition: rule.condition,
            nextStepId: stepIdMap[rule.to],
            priority: 1
          },
          {
            headers: { Authorization: `Bearer ${token}` }
          }
        );
      }

      alert("Workflow Created Successfully");

    } catch (err) {
      console.log(err);
      alert("Error");
    }
  };

  return (

    <Layout>

      <h2 className="text-2xl font-bold mb-6">
        Create Workflow
      </h2>

      {/*  ONLY DROPDOWN */}
      <select
        className="border p-2 w-full mb-4"
        onChange={(e) => handleChange(e.target.value)}
      >
        <option value="">Select Workflow</option>
        <option value="LEAVE">Leave Approval</option>
        <option value="LOAN">Bank Loan Approval</option>
      </select>

      {/* PREVIEW */}
      {config && (
        <div className="bg-white p-4 shadow">

          <h3 className="font-bold mb-2">Steps</h3>
          {config.steps.map((s, i) => (
            <div key={i}>{s.name}</div>
          ))}

          <h3 className="font-bold mt-4 mb-2">Rules</h3>
          {config.rules.map((r, i) => (
            <div key={i}>
              {config.steps[r.from].name} → {config.steps[r.to].name}
            </div>
          ))}

        </div>
      )}

      <button
        onClick={createWorkflow}
        className="bg-blue-500 text-white p-2 rounded mt-4"
      >
        Create Workflow
      </button>

    </Layout>
  );
};

export default WorkflowBuilder;