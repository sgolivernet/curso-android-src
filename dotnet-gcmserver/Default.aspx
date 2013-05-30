<%@ Page Title="Home Page" Language="C#" MasterPageFile="~/Site.master" AutoEventWireup="true"
    CodeBehind="Default.aspx.cs" Inherits="C2DMServer._Default" %>

<asp:Content ID="HeaderContent" runat="server" ContentPlaceHolderID="HeadContent">
</asp:Content>
<asp:Content ID="BodyContent" runat="server" ContentPlaceHolderID="MainContent">
    <h2>
        Pruebas ANDROID GCM
    </h2>
    <p>
        Usuario: <asp:TextBox ID="TxtUsuario" runat="server"></asp:TextBox>&nbsp;<asp:Button ID="Button3" runat="server" onclick="Button3_Click" 
            Text="Enviar GCM" />
        &nbsp;<asp:Label ID="LblResultadoMensaje" runat="server" Text="Label"></asp:Label>
    </p>
    <p>
        &nbsp;</p>
</asp:Content>
