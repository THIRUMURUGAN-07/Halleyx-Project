import { Link } from "react-router-dom";
import Navbar from "./Navbar";

const Layout = ({ children }) => {

  const role = localStorage.getItem("role");

  return (

    <div className="flex">

      {/* TOP NAVBAR */}
      <Navbar />

      {/* SIDEBAR */}
      <div className="w-60 bg-gray-900 text-white min-h-screen p-4 pt-20 fixed">

        <h2 className="text-xl font-bold mb-6">
          Workflow Engine
        </h2>

        <ul className="space-y-4">

          <li>
            <Link to="/dashboard" className="block hover:text-gray-300">
              Dashboard
            </Link>
          </li>

          {/* USER MENU */}
          {role === "ROLE_USER" && (
            <>
              <li>
                <Link to="/user-workflows" className="block hover:text-gray-300">
                  Workflows
                </Link>
              </li>

              <li>
                <Link to="/my-executions" className="block hover:text-gray-300">
                  Executions
                </Link>
              </li>
            </>
          )}

          {/* ADMIN MENU */}
          {role === "ROLE_ADMIN" && (
            <>
              <li>
                <Link to="/workflows" className="block hover:text-gray-300">
                  Workflows
                </Link>
              </li>

              <li>
                <Link to="/executions" className="block hover:text-gray-300">
                  Executions
                </Link>
              </li>
            </>
          )}

        </ul>

      </div>

      {/* MAIN CONTENT */}
      <div className="flex-1 ml-60 p-6 bg-gray-100 mt-16 min-h-screen">
        {children}
      </div>

    </div>
  );
};

export default Layout;