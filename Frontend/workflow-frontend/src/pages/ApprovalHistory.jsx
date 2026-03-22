import Layout from "../components/Layout";

const ApprovalHistory = () => {

  
  const data = [
    {
      id: 1,
      name: "Leave Approval",
      status: "Approved",
      userName: "THIRU"
    },
    {
      id: 2,
      name: "Leave Approval",
      status: "Rejected",
      userName: "THIRU"
    }
  ];

  return (
    <Layout>

      <h2 className="text-2xl font-bold mb-6">
        Approval History
      </h2>

      <table className="w-full bg-white shadow rounded">

        {/* HEADER */}
        <thead className="bg-gray-200">
          <tr>
            <th className="p-3">Workflow Name</th>
            <th className="p-3">User</th>
            <th className="p-3">Status</th>
          </tr>
        </thead>

        {/* BODY */}
        <tbody>

          {data.map((item) => (

            <tr key={item.id} className="border-t text-center">

              <td className="p-3">
                {item.name}
              </td>

              <td className="p-3">
                {item.userName}
              </td>

              <td className="p-3">

                {/*  Status Styling */}
                <span
                  className={`px-3 py-1 rounded text-white ${
                    item.status === "Approved"
                      ? "bg-green-500"
                      : "bg-red-500"
                  }`}
                >
                  {item.status}
                </span>

              </td>

            </tr>

          ))}

        </tbody>

      </table>

    </Layout>
  );
};

export default ApprovalHistory;