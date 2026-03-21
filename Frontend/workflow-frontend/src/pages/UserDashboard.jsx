import Layout from "../components/Layout";
import { Link } from "react-router-dom";

const UserDashboard = () => {

  const name = localStorage.getItem("name");
  const role = localStorage.getItem("role");

  return (

    <Layout>

      <h2 className="text-2xl font-bold mb-6">
        Welcome {role} {name}
      </h2>

      <div className="grid grid-cols-3 gap-4">

        <Link to="/userworkflows" className="bg-white p-6 shadow rounded">
          Execute Workflow
        </Link>

        <Link to="/executions" className="bg-white p-6 shadow rounded">
          My Executions
        </Link>

      </div>

    </Layout>
  );
};

export default UserDashboard;