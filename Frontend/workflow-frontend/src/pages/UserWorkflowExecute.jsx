import Layout from "../components/Layout";
import { useEffect, useState } from "react";
import axios from "axios";

const UserWorkflowExecute = () => {

  const [workflows, setWorkflows] = useState([]);
  const [selectedWorkflow, setSelectedWorkflow] = useState(null);

  const [inputValue, setInputValue] = useState("");
  const [inputKey, setInputKey] = useState("");
  const [inputLabel, setInputLabel] = useState("");

  const token = localStorage.getItem("token");

  // LOAD WORKFLOWS
  const loadWorkflows = async () => {

    const res = await axios.get(
      "http://localhost:8080/workflows",
      {
        headers: {
          Authorization: `Bearer ${token}`
        }
      }
    );

    setWorkflows(res.data);
  };

  useEffect(() => {
    loadWorkflows();
  }, []);

  // SELECT WORKFLOW
  const handleSelect = (workflow) => {

    setSelectedWorkflow(workflow);
    setInputValue("");

    //  DYNAMIC FIELD BASED ON WORKFLOW NAME
    if (workflow.name === "Leave Approval") {
      setInputKey("days");
      setInputLabel("Enter Number of Days");
    } else if (workflow.name === "Bank Loan Approval") {
      setInputKey("amount");
      setInputLabel("Enter Loan Amount");
    }
  };

  // START EXECUTION
  const startExecution = async () => {

  if (!selectedWorkflow) {
    alert("Select a workflow first");
    return;
  }

  if (!inputValue) {
    alert("Enter required value");
    return;
  }

  try {

    const res = await axios.post(
  "http://localhost:8080/executions",
  {
    workflowId: selectedWorkflow.id,
    data: JSON.stringify({
      [inputKey]: Number(inputValue)
    })
  },
  {
    headers: {
      Authorization: `Bearer ${token}`
    }
  }
);

    console.log(res.data);
    alert("Workflow Started Successfully");
    console.log(res.data.data);
    setSelectedWorkflow(null);
    setInputValue("");

  } catch (err) {
    console.log(err);
    alert("Error starting workflow");
  }
};

  return (

    <Layout>

      <h2 className="text-2xl font-bold mb-4">
        Execute Workflow
      </h2>

      {/* LIST VIEW */}
      {!selectedWorkflow && workflows.map(w => (

        <div
          key={w.id}
          className="bg-white p-4 rounded shadow mb-3 flex justify-between items-center"
        >

          <span>{w.name}</span>

          <button
            onClick={() => handleSelect(w)}
            className="bg-blue-500 text-white px-4 py-2 rounded"
          >
            Select
          </button>

        </div>

      ))}

      {/* FORM VIEW */}
      {selectedWorkflow && (

        <div className="bg-white p-6 rounded shadow w-96">

          <h3 className="text-xl font-bold mb-4">
            {selectedWorkflow.name}
          </h3>

          {/* 🔥 DYNAMIC INPUT */}
          <input
            type="number"
            className="border p-2 w-full mb-3"
            placeholder={inputLabel}
            value={inputValue}
            onChange={(e) => setInputValue(e.target.value)}
          />

          <button
            onClick={startExecution}
            className="bg-green-500 text-white w-full p-2 rounded"
          >
            Submit
          </button>

          <button
            onClick={() => setSelectedWorkflow(null)}
            className="bg-gray-400 text-white w-full p-2 rounded mt-2"
          >
            Back
          </button>

        </div>

      )}

    </Layout>
  );
};

export default UserWorkflowExecute;