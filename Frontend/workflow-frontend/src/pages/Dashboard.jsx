import Layout from "../components/Layout";
import { useEffect, useState } from "react";
import axios from "axios";

const Dashboard = () => {


const[name,setName] = useState("");
const[role,setRole] = useState("");
  useEffect(() => {

    const fetchEmail = async () => {

      const token = localStorage.getItem("token");

      const res = await axios.get(
        "http://localhost:8080/auth/email",
        {
          headers:{
            Authorization:`Bearer ${token}`
          }
        }
      );

    setName(res.data.name);
    setRole(res.data.role);

    };

    fetchEmail();

  },[]);



 
  return (

    <Layout>

      <h2 className="text-2xl font-bold mb-6">

        Welcome {role} {name}

      </h2>

      <div className="grid grid-cols-3 gap-4">

        <div className="bg-white p-6 rounded shadow">
          Total Workflows
        </div>

        <div className="bg-white p-6 rounded shadow">
          Running Executions
        </div>

        <div className="bg-white p-6 rounded shadow">
          Pending Approvals
        </div>

      </div>

    </Layout>
  );
};

export default Dashboard;