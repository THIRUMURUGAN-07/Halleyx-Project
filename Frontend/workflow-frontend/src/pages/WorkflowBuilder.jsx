import { useState } from "react";
import axios from "axios";
import Layout from "../components/Layout";

const WorkflowBuilder = () => {

  const token = localStorage.getItem("token");

  const [workflowName,setWorkflowName] = useState("");
  const [steps,setSteps] = useState([]);

  
  const addStep = () => {

    setSteps([
      ...steps,
      {
        tempId: Date.now().toString(),
        name:"",
        stepType:"TASK",
        rules:[]
      }
    ]);
  };

  const updateStep = (index,field,value) => {

    const updated = [...steps];
    updated[index][field] = value;
    setSteps(updated);
  };

  const addRule = (stepIndex) => {

    const updated = [...steps];

    updated[stepIndex].rules.push({
      condition:"",
      nextStepTempId:"",
      priority:1
    });

    setSteps(updated);
  };

  const updateRule = (stepIndex,ruleIndex,field,value) => {

    const updated = [...steps];
    updated[stepIndex].rules[ruleIndex][field] = value;
    setSteps(updated);
  };

  const saveWorkflow = async () => {

  try {

    console.log("START");

    const stepIdMap = {};

    // STEP 1
    for(let step of steps){

      console.log("Creating step:", step.name);

      const stepRes = await axios.post(
        "http://localhost:8080/steps",
        {
          name:step.name,
          stepType:step.stepType
        },
        {
          headers:{ Authorization:`Bearer ${token}` }
        }
      );

      stepIdMap[step.tempId] = stepRes.data.id;
    }

    console.log("Steps created");

    // STEP 2
    const firstStepId = stepIdMap[steps[0].tempId];

    const workflowRes = await axios.post(
      "http://localhost:8080/workflows",
      {
        name:workflowName,
        version:1,
        isActive:true,
        startStepId:firstStepId
      },
      {
        headers:{ Authorization:`Bearer ${token}` }
      }
    );

    const workflowId = workflowRes.data.id;

    console.log("Workflow created");

    // STEP 3
    for(let step of steps){

      const realStepId = stepIdMap[step.tempId];

      await axios.post(
        `http://localhost:8080/steps/${realStepId}/step`,
        { workflowId },
        {
          headers:{ Authorization:`Bearer ${token}` }
        }
      );
    }

    console.log("Steps updated");

    // STEP 4 (LIKELY ERROR HERE)
    for(let step of steps){

      const realStepId = stepIdMap[step.tempId];

      for(let rule of step.rules){

        console.log("Creating rule for step:", realStepId);

        await axios.post(
          `http://localhost:8080/rules/${realStepId}/rule`,
          {
            condition:rule.condition,
            nextStepId:stepIdMap[rule.nextStepTempId],
            priority:rule.priority
          },
          {
            headers:{ Authorization:`Bearer ${token}` }
          }
        );
      }
    }

    console.log("Rules created");

    alert("Workflow Created Successfully");

  } catch (error) {

    console.error("ERROR:", error.response?.data || error.message);

    alert("Error occurred: " + (error.response?.status || ""));

  }

};

  return (

    <Layout>

      <h2 className="text-2xl font-bold mb-6">
        Workflow Builder
      </h2>

      <input
        className="border p-2 mb-6 w-full"
        placeholder="Workflow Name"
        onChange={(e)=>setWorkflowName(e.target.value)}
      />

      {steps.map((step,index)=>(

        <div key={step.tempId} className="bg-white p-4 mb-4 shadow">

          <input
            className="border p-2 mb-2 w-full"
            placeholder="Step Name"
            onChange={(e)=>updateStep(index,"name",e.target.value)}
          />

          <select
            className="border p-2 mb-3 w-full"
            onChange={(e)=>updateStep(index,"stepType",e.target.value)}
          >
            <option value="TASK">TASK</option>
            <option value="APPROVAL">APPROVAL</option>
            <option value="NOTIFICATION">NOTIFICATION</option>
          </select>

          <h4 className="font-bold mb-2">Rules</h4>

          {step.rules.map((rule,rIndex)=>(

            <div key={rIndex} className="border p-3 mb-2">

              <input
                className="border p-2 mb-2 w-full"
                placeholder="Condition"
                onChange={(e)=>updateRule(index,rIndex,"condition",e.target.value)}
              />


              <select
                className="border p-2 w-full"
                onChange={(e)=>updateRule(index,rIndex,"nextStepTempId",e.target.value)}
              >
                <option value="">Select Next Step</option>

                {steps.map(s => (
                  <option key={s.tempId} value={s.tempId}>
                    {s.name || "Unnamed Step"}
                  </option>
                ))}

              </select>

            </div>

          ))}

          <button
            onClick={()=>addRule(index)}
            className="bg-green-500 text-white p-2 rounded"
          >
            Add Rule
          </button>

        </div>

      ))}

      <button
        onClick={addStep}
        className="bg-blue-500 text-white p-2 rounded mr-4"
      >
        Add Step
      </button>

      <button
        onClick={saveWorkflow}
        className="bg-purple-600 text-white p-2 rounded"
      >
        Save Workflow
      </button>

    </Layout>

  );

};

export default WorkflowBuilder;