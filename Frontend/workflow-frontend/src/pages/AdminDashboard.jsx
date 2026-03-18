import Layout from "../components/Layout";
import { Link } from "react-router-dom";

const AdminDashboard = () => {

  const name = localStorage.getItem("name");

  return (

    <Layout>

      <h2 className="text-2xl font-bold mb-6">
        Welcome Admin {name}
      </h2>

      <div className="grid grid-cols-3 gap-4">

        <Link to="/workflowbuilder" className="bg-white p-6 shadow rounded">
          Create Workflow
        </Link>

        <Link to="/executions" className="bg-white p-6 shadow rounded">
          View Executions
        </Link>

        <Link to="/logs" className="bg-white p-6 shadow rounded">
          Execution Logs
        </Link>

      </div>

    </Layout>
  );
};

export default AdminDashboard;