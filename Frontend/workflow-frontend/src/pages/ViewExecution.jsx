import Layout from "../components/Layout";

const ViewExecution = () => {

  
  const executions = [
    {
      id: 1,
      userName: "Thiru",
      workflowName: "Leave Approval",
      status: "In Progress",
      startedTime: "2026-03-21 10:30 AM"
    },
    {
      id: 2,
      userName: "Murugan",
      workflowName: "Leave Approval",
      status: "Completed",
      startedTime: "2026-03-20 02:15 PM"
    }
  ];

  return (
    <Layout>

      <h2 className="text-2xl font-bold mb-6">
        All Executions
      </h2>

      <table className="w-full bg-white shadow rounded">

        {/* HEADER */}
        <thead className="bg-gray-200">
          <tr>
            <th className="p-3">User Name</th>
            <th className="p-3">Workflow</th>
            <th className="p-3">Status</th>
            <th className="p-3">Started Time</th>
          </tr>
        </thead>

        {/* BODY */}
        <tbody>

          {executions.map((exec) => (

            <tr key={exec.id} className="border-t text-center">

              <td className="p-3">{exec.userName}</td>

              <td className="p-3">{exec.workflowName}</td>

              <td className="p-3">

                {/*  Status Badge */}
                <span
                  className={`px-3 py-1 rounded text-white ${
                    exec.status === "Completed"
                      ? "bg-green-500"
                      : exec.status === "Rejected"
                      ? "bg-red-500"
                      : "bg-yellow-500"
                  }`}
                >
                  {exec.status}
                </span>

              </td>

              <td className="p-3">{exec.startedTime}</td>

            </tr>

          ))}

        </tbody>

      </table>

    </Layout>
  );
};

export default ViewExecution;