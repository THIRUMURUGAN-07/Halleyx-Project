import { Link } from "react-router-dom";
import { FaHome, FaProjectDiagram, FaPlay } from "react-icons/fa";

const Sidebar = () => {

  return (
    <div className="w-64 bg-gray-900 text-white h-screen p-6">

      <h2 className="text-2xl font-bold mb-8">
        Workflow Engine
      </h2>

      <nav className="flex flex-col gap-4">

        <Link
          to="/dashboard"
          className="flex items-center gap-2 hover:bg-gray-700 p-2 rounded"
        >
          <FaHome/>
          Dashboard
        </Link>

        <Link
          to="/workflows"
          className="flex items-center gap-2 hover:bg-gray-700 p-2 rounded"
        >
          <FaProjectDiagram/>
          Workflows
        </Link>

        <Link
          to="/executions"
          className="flex items-center gap-2 hover:bg-gray-700 p-2 rounded"
        >
          <FaPlay/>
          Executions
        </Link>

      </nav>

    </div>
  );
};

export default Sidebar;