import { useEffect,useState } from "react";
import axios from "axios";
import Layout from "../components/Layout";

const Executions = () => {

  const [executions,setExecutions] = useState([]);

  const loadExecutions = async () => {

    const token = localStorage.getItem("token");

    const res = await axios.get(
      "http://localhost:8080/executions",
      {
        headers:{
          Authorization:`Bearer ${token}`
        }
      }
    );

    setExecutions(res.data);
  };

  useEffect(()=>{
    loadExecutions();
  },[]);

  return (

    <Layout>

      <h2 className="text-2xl font-bold mb-6">
        Workflow Executions
      </h2>

      <div className="grid gap-4">

        {executions.map(e => (

          <div key={e.id} className="bg-white p-4 shadow rounded">

            <p><b>Workflow:</b> {e.workflowId}</p>
            <p><b>Status:</b> {e.status}</p>
            <p><b>Current Step:</b> {e.currentStepId}</p>

          </div>

        ))}

      </div>

    </Layout>
  );
};

export default Executions;