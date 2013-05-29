using System.Collections.Generic;
using System.Data.SqlClient;

namespace ServicioWebRest.Areas.Api.Models
{
    public class ClienteManager
    {
        private static string cadenaConexion = 
            @"Data Source=SGOLIVER-PC\SQLEXPRESS;Initial Catalog=DBCLIENTES;Integrated Security=True";

        public bool InsertarCliente(Cliente cli)
        {
            SqlConnection con = new SqlConnection(cadenaConexion);

            con.Open();

            string sql = "INSERT INTO Clientes (Nombre, Telefono) VALUES (@nombre, @telefono)";

            SqlCommand cmd = new SqlCommand(sql,con);

            cmd.Parameters.Add("@nombre", System.Data.SqlDbType.NVarChar).Value = cli.Nombre;
            cmd.Parameters.Add("@telefono", System.Data.SqlDbType.Int).Value = cli.Telefono;

            int res = cmd.ExecuteNonQuery();

            con.Close();

            return (res == 1);
        }

        public bool ActualizarCliente(Cliente cli) 
        {
            SqlConnection con = new SqlConnection(cadenaConexion);

            con.Open();

            string sql = "UPDATE Clientes SET Nombre = @nombre, Telefono = @telefono WHERE IdCliente = @idcliente";

            SqlCommand cmd = new SqlCommand(sql,con);

            cmd.Parameters.Add("@nombre", System.Data.SqlDbType.NVarChar).Value = cli.Nombre;
            cmd.Parameters.Add("@telefono", System.Data.SqlDbType.Int).Value = cli.Telefono;
            cmd.Parameters.Add("@idcliente", System.Data.SqlDbType.Int).Value = cli.Id;

            int res = cmd.ExecuteNonQuery();

            con.Close();

            return (res == 1);
        }

        public Cliente ObtenerCliente(int id)
        {
            Cliente cli = null;

            SqlConnection con = new SqlConnection(cadenaConexion);

            con.Open();

            string sql = "SELECT Nombre, Telefono FROM Clientes WHERE IdCliente = @idcliente";

            SqlCommand cmd = new SqlCommand(sql,con);

            cmd.Parameters.Add("@idcliente", System.Data.SqlDbType.NVarChar).Value = id;

            SqlDataReader reader = 
                cmd.ExecuteReader(System.Data.CommandBehavior.CloseConnection);

            if(reader.Read())
            {
                cli = new Cliente();
                cli.Id = id;
                cli.Nombre = reader.GetString(0);
                cli.Telefono = reader.GetInt32(1);
            }

            reader.Close();

            return cli;
        }

        public List<Cliente> ObtenerClientes()
        {
            List<Cliente> lista = new List<Cliente>();

            SqlConnection con = new SqlConnection(cadenaConexion);

            con.Open();

            string sql = "SELECT IdCliente, Nombre, Telefono FROM Clientes";

            SqlCommand cmd = new SqlCommand(sql,con);

            SqlDataReader reader = 
                cmd.ExecuteReader(System.Data.CommandBehavior.CloseConnection);

            while (reader.Read())
            {
                Cliente cli = new Cliente();

                cli = new Cliente();
                cli.Id = reader.GetInt32(0);
                cli.Nombre = reader.GetString(1);
                cli.Telefono = reader.GetInt32(2);

                lista.Add(cli);
            }

            reader.Close();

            return lista;
        }

        public bool EliminarCliente(int id) 
        {
            SqlConnection con = new SqlConnection(cadenaConexion);

            con.Open();

            string sql = "DELETE FROM Clientes WHERE IdCliente = @idcliente";

            SqlCommand cmd = new SqlCommand(sql, con);

            cmd.Parameters.Add("@idcliente", System.Data.SqlDbType.Int).Value = id;

            int res = cmd.ExecuteNonQuery();

            con.Close();

            return (res == 1); 
        }
    }
}