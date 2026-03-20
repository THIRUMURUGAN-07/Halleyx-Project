import Layout from "../components/Layout";
import { useEffect, useState } from "react";
import axios from "axios";

const WorkflowList = () => {

  const [workflows, setWorkflows] = useState([]);

  const loadWorkflows = async () => {

    const token = localStorage.getItem("token");

    try {

      const res = await axios.get(
        "http://localhost:8080/workflows",
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        }
      );

      setWorkflows(res.data);

    } catch (err) {
      console.log("ERROR:", err);
    }
  };

  const deleteWorkflow = async (id) => {

    const token = localStorage.getItem("token");

    try {

      await axios.delete(
        `http://localhost:8080/workflows/${id}`, // ✅ FIXED URL
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        }
      );

      alert("Deleted successfully");

      loadWorkflows(); // ✅ reload after delete

    } catch (err) {

      console.log("DELETE ERROR:", err);

      if (err.response?.status === 403) {
        alert("Access Denied (Only ADMIN can delete)");
      }

    }
  };

  useEffect(() => {
    loadWorkflows();
  }, []);

  return (

    <Layout>

      <h2 className="text-2xl font-bold mb-4">
        Workflows
      </h2>

      {workflows.map(w => (

        <div
          key={w.id}
          className="bg-white p-4 rounded shadow mb-3 flex justify-between items-center"
        >

          <span>{w.name}</span>

          <button
            onClick={() => deleteWorkflow(w.id)}
            className="bg-red-500 text-white px-4 py-2 rounded"
          >
            Delete
          </button>

        </div>

      ))}

    </Layout>
  );
};

export default WorkflowList;