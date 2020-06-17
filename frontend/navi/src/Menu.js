import React, { Component } from 'react';
import './css/Menu.css'
import { Link } from 'react-router-dom';
import Avatar from '@material-ui/core/Avatar';
import SimpleMenu from '../src/optMenu'

// Bootstrap
import {
    Image,
    Navbar,
    Nav
} from 'react-bootstrap';

//Icons
import HomeIcon from '@material-ui/icons/Home';
import MapIcon from '@material-ui/icons/Map';
import LibraryBooksIcon from '@material-ui/icons/LibraryBooks';
import AccountCircleIcon from '@material-ui/icons/AccountCircle';

export default class Menu extends Component {

    componentDidMount() {
        this.verficarUsuario();
    }

    verficarUsuario = () => {
        if (sessionStorage.getItem('@NAVI/tipo') == null) {
            window.location = "/";
        }
    }

    menuComprador = () => {
        return (
            <div>
                <Nav.Link href="/home">
                    <HomeIcon className="icon" />
                    <span>Procurar Loja</span>
                </Nav.Link>
                <Nav.Link href="/pedidos">
                    <LibraryBooksIcon className="icon" />
                    <span>Meus Pedidos</span>
                </Nav.Link>
            </div>
        );
    }

    menuVendedor = () => {
        return (
            <div>
                <Nav.Link href="/pedidos">
                    <LibraryBooksIcon className="icon" />
                    <span>Pedidos</span>
                </Nav.Link>
                <Nav.Link href="/entregadores">
                    <AccountCircleIcon className="icon" />
                    <span>Entregadores</span>
                </Nav.Link>
            </div>
        );
    }

    menuEntregador = () => {
        return (
            <div>
                <Nav.Link href="/pedidos">
                    <LibraryBooksIcon className="icon" />
                    <span>Pedidos</span>
                </Nav.Link>
            </div>
        );
    }

    render() {
        return (
            <div>
                <Navbar fixed="top" bg="light" variant="light" className="text-navbar shadow-sm border-bottom">
                    <Navbar.Brand>
                        <Link to="/home">
                            <Image
                                src={require('./img/navi-logo.png')}
                                width="40"
                                height="40"
                                className="d-inline-block align-top ml-2"
                                alt="Navi"
                            />
                        </Link>
                    </Navbar.Brand>
                    <div className="ml-auto mr-2">
                        <span className="mr-3 text-dark nome-user">Olá, {sessionStorage.getItem('@NAVI/nome')}</span>
                        <Avatar
                            className="d-inline-block"
                            src={require('./img/avatar.jpg')}
                        />
                    </div>
                    <SimpleMenu/>
                </Navbar>
                <div className="espaco-topo" />

                <Nav className="flex-column bg-light p-3 sidebar border-right shadow-sm">
                    <h4 className="text-primary m-2 mb-3 font-weight-light">Menu</h4>
                    {sessionStorage.getItem('@NAVI/tipo') == "Comprador" ? this.menuComprador() : sessionStorage.getItem('@NAVI/tipo') == "Vendedor" ? this.menuVendedor() : this.menuEntregador()}
                </Nav>
                <div className="espaco-menu" />
            </div>
        );
    }
}