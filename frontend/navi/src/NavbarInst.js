import React, { Component } from 'react';
import './css/NavbarInst.css'
import { Link } from 'react-router-dom';

// Bootstrap
import { Image, Navbar, Nav } from 'react-bootstrap';

class NavbarInst extends Component {

    componentDidMount() {
        if(sessionStorage.getItem('@NAVI/nome') != null){
            window.location = "/home";
        }
    }

    render() {
        return (
            <Navbar fixed="top" bg="light" variant="light" className="text-navbar">
                <Navbar.Brand>
                    <Link to="/">
                        <Image
                            src={require('./img/navi-logo.png')}
                            width="30"
                            height="30"
                            className="d-inline-block align-top"
                            alt="Navi"
                        />
                    </Link>
                </Navbar.Brand>
                <Navbar.Toggle aria-controls="responsive-navbar-nav" />
                <Nav className="ml-auto">
                    <Nav.Link href="/">Início</Nav.Link>
                    <Nav.Link href="/#sobre">Sobre</Nav.Link>
                    <Nav.Link href="/#parceiros">Parceiros</Nav.Link>
                    {/* <Nav.Link href="https://guilherme-mendes.outsystemscloud.com/GoogleMapsDemo/HomeEntry.aspx?_ts=637219615460909232">Maps BETA</Nav.Link> */}
                    <Nav.Link className="link-nav" href="/cadastro">Cadastrar</Nav.Link>
                    <Nav.Link className="link-nav" href="/login">
                        <span className="link-nav-btn">Entrar</span>
                    </Nav.Link>
                </Nav>
            </Navbar>
        );
    }
}

export default NavbarInst;