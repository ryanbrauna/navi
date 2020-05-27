import React, { Component } from 'react';
import './css/Menu.css'
import { Link } from 'react-router-dom';
import Avatar from '@material-ui/core/Avatar';

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
import RoomIcon from '@material-ui/icons/Room';

export default class NavbarInst extends Component {
    render() {
        return (
            <div>
                {
                    console.log(sessionStorage.getItem('@NAVI/tipo'))
                }
                <Navbar fixed="top" bg="light" variant="light" className="text-navbar shadow-sm">
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
                            alt="Remy Sharp"
                            src={require('./img/caminhao-style.jpg')}
                        />
                    </div>
                </Navbar>
                <div className="espaco-topo" />

                <Nav className="flex-column bg-light p-3 sidebar">
                    <Nav.Link href="/home">
                        <HomeIcon className="icon" />
                        <span>Procurar Loja</span>
                    </Nav.Link>
                    <Nav.Link href="https://guilherme-mendes.outsystemscloud.com/GoogleMapsDemo/HomeEntry.aspx?_ts=637219615460909232">
                        <MapIcon className="icon" />
                        <span>Maps BETA</span>
                    </Nav.Link>
                    <Nav.Link href="/home">
                        <LibraryBooksIcon className="icon" />
                        <span>Meus Pedidos</span>
                    </Nav.Link>
                    <Nav.Link href="/home">
                        <RoomIcon className="icon" />
                        <span>Acompanhar entrega</span>
                    </Nav.Link>
                </Nav>
            </div>
        );
    }
}