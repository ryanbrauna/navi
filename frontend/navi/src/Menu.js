import React, { Component } from 'react';
import './css/Menu.css'
import { Link } from 'react-router-dom';
import Avatar from '@material-ui/core/Avatar';

// Bootstrap
import { Image, Navbar } from 'react-bootstrap';

export default class NavbarInst extends Component {
    render() {
        return (
            <div>
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
                        <Avatar
                            alt="Remy Sharp"
                            src={require('./img/caminhao-style.jpg')}
                        />
                    </div>
                </Navbar>
                <div className="espaco-topo" />
            </div>
        );
    }
}