import Layout from "../components/Layout";
import { Link } from "react-router-dom";

const ManagerDashboard = () => {

  const name = localStorage.getItem("name");
  const role =localStorage.getItem("role");
  return (

    <Layout>

      <h2 className="text-2xl font-bold mb-6">
        Welcome {role} {name}
      </h2>

      <div className="grid grid-cols-2 gap-4">

        <Link to="/approval" className="bg-white p-6 shadow rounded">
          Pending Approvals
        </Link>

        <Link to="/approval-history" className="bg-white p-6 shadow rounded">
          Approval History
        </Link>

      </div>

    </Layout>
  );
};

export default ManagerDashboard;