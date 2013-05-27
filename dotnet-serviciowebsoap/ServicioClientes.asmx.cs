using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using System.Data.SqlClient;

namespace ServicioWebSoap
{
    /// <summary>
    /// Summary description for ServicioClientes
    /// </summary>
    [WebService(Namespace = "http://sgoliver.net/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    // [System.Web.Script.Services.ScriptService]
    public class ServicioClientes : System.Web.Services.WebService
    {
        [WebMethod]
        public int NuevoClienteSimple(string nombre, int telefono)
        {
            SqlConnection con = new SqlConnection(@"Data Source=SGOLIVER-PC\SQLEXPRESS;Initial Catalog=DBCLIENTES;Integrated Security=True");

            con.Open();

            string sql = "INSERT INTO Clientes (Nombre, Telefono) VALUES (@nombre, @telefono)";

            SqlCommand cmd = new SqlCommand(sql, con);

            cmd.Parameters.Add("@nombre", System.Data.SqlDbType.NVarChar).Value = nombre;
            cmd.Parameters.Add("@telefono", System.Data.SqlDbType.Int).Value = telefono;

            int res = cmd.ExecuteNonQuery();

            con.Close();

            return res;
        }

        [WebMethod]
        public int NuevoClienteObjeto(Cliente cliente)
        {
            SqlConnection con = new SqlConnection(@"Data Source=SGOLIVER-PC\SQLEXPRESS;Initial Catalog=DBCLIENTES;Integrated Security=True");

            con.Open();

            string sql = "INSERT INTO Clientes (Nombre, Telefono) VALUES (@nombre, @telefono)";

            SqlCommand cmd = new SqlCommand(sql, con);

            cmd.Parameters.Add("@nombre", System.Data.SqlDbType.NVarChar).Value = cliente.Nombre;
            cmd.Parameters.Add("@telefono", System.Data.SqlDbType.Int).Value = cliente.Telefono;

            int res = cmd.ExecuteNonQuery();

            con.Close();

            return res;
        }

        [WebMethod]
        public Cliente[] ListadoClientes()
        {
            SqlConnection con = new SqlConnection(@"Data Source=SGOLIVER-PC\SQLEXPRESS;Initial Catalog=DBCLIENTES;Integrated Security=True");

            con.Open();

            string sql = "SELECT IdCliente, Nombre, Telefono FROM Clientes";

            SqlCommand cmd = new SqlCommand(sql, con);

            SqlDataReader reader = cmd.ExecuteReader();

            List<Cliente> lista = new List<Cliente>();

            while (reader.Read())
            { 
                lista.Add(new Cliente(reader.GetInt32(0), reader.GetString(1), reader.GetInt32(2)));
            }

            con.Close();

            return lista.ToArray();
        }
    }
}
