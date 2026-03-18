import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";


import WorkflowList from "./pages/WorkflowList";
import Login from "./pages/Login";
import Register from "./pages/Register";
import ProtectedRoute from "./routes/ProtectedRoute";
import DashboardRouter from "./pages/DashboardRouter";
import WorkflowBuilder from "./pages/WorkflowBuilder";
import Executions from "./pages/Executions";
import ExecutionLogs from "./pages/ExecutionLogs";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        {/* Default route */}
        <Route path="/" element={<Navigate to="/dashboard" />} />

        {/* Login */}
        <Route path="/login" element={<Login />} />

        {/* Register */}

        <Route path="/Register" element={<Register />} />

        {/* Dashboard */}
        <Route path="/dashboard" element={<DashboardRouter />} />

        {/* Workflows */}
        <Route
          path="/workflows"
          element={
            <ProtectedRoute>
              <WorkflowList />
            </ProtectedRoute>
          }
        />
        <Route
          path="/workflowbuilder"
          element={
            <ProtectedRoute>
              <WorkflowBuilder />
            </ProtectedRoute>
          }
        /> 
        <Route
          path="/executions"
          element={
            <ProtectedRoute>
              <Executions />
            </ProtectedRoute>
          }
        />
        <Route
          path="/logs"
          element={
            <ProtectedRoute>
              <ExecutionLogs />
            </ProtectedRoute>
          }
        />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
