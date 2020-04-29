import React, { Component } from 'react';
import './css/Login.css'
import { Link } from 'react-router-dom';

// Components
import NavbarInst from './NavbarInst';

// Bootstrap
import 'bootstrap/dist/css/bootstrap.css';
import {
    Row,
    Col,
    Form,
    Button
} from 'react-bootstrap';

class Login extends Component {
    render() {
        return (
            <div>
                <NavbarInst />

                <Row className="justify-content-center m-0">
                    <Col md="6" className="box-login bg-light mx-3 p-5 rounded shadow">
                        <h4 className="mb-3">Entrar</h4>
                        <Form>
                            <Form.Group controlId="formEmail">
                                <Form.Control type="email" placeholder="Email" />
                                <Form.Label className="text-muted">Digite seu endereço de E-mail.</Form.Label>
                            </Form.Group>

                            <Form.Group controlId="formSenha">
                                <Form.Control type="password" placeholder="Senha" />
                                <Form.Label className="text-muted">Digite sua senha Senha.</Form.Label>
                            </Form.Group>

                            <Link className="text-light" to="/home">
                                <Button variant="primary" type="submit">Entrar</Button>
                            </Link>
                        </Form>
                    </Col>
                </Row>

                <div id="fim" className="fixed-bottom">
                    <span> NAVI © 2020</span>
                </div >
            </div>
        );
    }
}

export default Login;