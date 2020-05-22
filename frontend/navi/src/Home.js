import React, { Component } from 'react';
import './css/Home.css';

// Components
import Menu from './Menu';

// Bootstrap
import 'bootstrap/dist/css/bootstrap.css';
import {
    // Row,
    // Col,
    // Form,
    // Button,
    Nav
} from 'react-bootstrap';

//Icons
import HomeIcon from '@material-ui/icons/Home';
import MapIcon from '@material-ui/icons/Map';
import LibraryBooksIcon from '@material-ui/icons/LibraryBooks';

export default class Home extends Component {
    render() {
        return (
            <div>
                <Menu />
                <Nav className="flex-column bg-light p-3 sidebar">
                    <Nav.Link href="/home">
                        <HomeIcon className="icon" />
                        <span>Home</span>
                    </Nav.Link>
                    <Nav.Link href="https://guilherme-mendes.outsystemscloud.com/GoogleMapsDemo/HomeEntry.aspx?_ts=637219615460909232">
                        <MapIcon className="icon" />
                        <span>Maps BETA</span>
                    </Nav.Link>
                    <Nav.Link href="/home">
                        <LibraryBooksIcon className="icon" />
                        <span>Pedidos</span>
                    </Nav.Link>
                </Nav>
            </div>
        );
    }
}