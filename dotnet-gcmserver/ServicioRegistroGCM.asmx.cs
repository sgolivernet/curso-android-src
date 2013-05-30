using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using System.Data.SqlClient;

namespace C2DMServer
{
    /// <summary>
    /// Summary description for ServicioRegistroC2DM
    /// </summary>
    [WebService(Namespace = "http://sgoliver.net/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    // [System.Web.Script.Services.ScriptService]
    public class ServicioRegistroGCM : System.Web.Services.WebService
    {
        [WebMethod]
        public int RegistroCliente(string usuario, string regGCM)
        {
            SqlConnection con =
                new SqlConnection(
                    @"Data Source=SGOLIVER-PC\SQLEXPRESS;Initial Catalog=DBUSUARIOS;Integrated Security=True");

            con.Open();

            string cod = CodigoCliente(usuario);

            int res = 0;
            string sql = "";

            if (cod == null)
                sql = "INSERT INTO Usuarios (NombreUsuario, CodigoC2DM) VALUES (@usuario, @codigo)";    
            else
                sql = "UPDATE Usuarios SET CodigoC2DM = @codigo WHERE NombreUsuario = @usuario";

            SqlCommand cmd = new SqlCommand(sql, con);

            cmd.Parameters
                .Add("@usuario", System.Data.SqlDbType.NVarChar).Value = usuario;
            cmd.Parameters
                .Add("@codigo", System.Data.SqlDbType.NVarChar).Value = regGCM;

            res = cmd.ExecuteNonQuery();

            con.Close();

            return res;
        }

        [WebMethod]
        public string CodigoCliente(string usuario)
        {
            SqlConnection con =
                new SqlConnection(
                    @"Data Source=SGOLIVER-PC\SQLEXPRESS;Initial Catalog=DBUSUARIOS;Integrated Security=True");

            con.Open();

            string sql = "SELECT CodigoC2DM FROM Usuarios WHERE NombreUsuario = @usuario";

            SqlCommand cmd = new SqlCommand(sql, con);

            cmd.Parameters.Add("@usuario", System.Data.SqlDbType.NVarChar).Value = usuario;

            string cod = (String)cmd.ExecuteScalar();

            con.Close();

            return cod;
        }
    }
}
