import Layout from "../components/Layout";
import { useEffect, useState } from "react";
import axios from "axios";

const WorkflowList = () => {

  const [workflows,setWorkflows] = useState([]);
  const [name,setName] = useState("");

  const loadWorkflows = async () => {

    const token = localStorage.getItem("token");

    const res = await axios.get(
      "http://localhost:8080/workflows",
      {
        headers:{
          Authorization:`Bearer ${token}`
        }
      }
    );

    setWorkflows(res.data);
  };

  const createWorkflow = async () => {

    const token = localStorage.getItem("token");

    await axios.post(
      "http://localhost:8080/workflows",
      {name},
      {
        headers:{
          Authorization:`Bearer ${token}`
        }
      }
    );

    setName("");

    loadWorkflows();
  };

  useEffect(()=>{

    loadWorkflows();

  },[]);

  return (

    <Layout>

      <h2 className="text-2xl font-bold mb-4">
        Workflows
      </h2>

      <div className="mb-6">

        <input
          className="border p-2 mr-2"
          placeholder="Workflow name"
          value={name}
          onChange={(e)=>setName(e.target.value)}
        />

        <button
          onClick={createWorkflow}
          className="bg-green-500 text-white px-4 py-2"
        >
          Create
        </button>

      </div>

      {workflows.map(w => (

        <div
          key={w.id}
          className="bg-white p-4 rounded shadow mb-3"
        >
          {w.name}
        </div>

      ))}

    </Layout>
  );
};

export default WorkflowList;