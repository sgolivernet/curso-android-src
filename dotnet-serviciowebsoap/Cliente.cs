using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ServicioWebSoap
{
    public class Cliente
    {
        public int Id {get; set;}
        public string Nombre {get; set;}
        public int Telefono {get; set;}

        public Cliente()
        {
            this.Id = 0;
            this.Nombre = "";
            this.Telefono = 0;
        }
        
        public Cliente(int id, string nombre, int telefono)
        {
            this.Id = id;
            this.Nombre = nombre;
            this.Telefono = telefono;
        }
    }
}