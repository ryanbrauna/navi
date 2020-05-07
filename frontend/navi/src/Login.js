import React, { Component } from 'react';
import './css/Login.css'
//import { Link } from 'react-router-dom';
import axios from 'axios';
import swal from 'sweetalert';

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

export default class Login extends Component {

    constructor(props) {
        super(props);
        this.state = {
            compradores: [],
            vendedores: [],
            email: '',
            senha: ''
        };
        this.login = this.login.bind(this);
    }

    componentDidMount() {
        axios.get("http://localhost:8080/compradores").then((data) => {
            this.setState({ compradores: data.data })
        });
        axios.get("http://localhost:8080/vendedores").then((data) => {
            this.setState({ vendedores: data.data })
        });
    }

    login() {
        console.log(this.state.compradores[0]);

        if (this.state.compradores[0].email == (this.state.email) &&
            this.state.compradores[0].senha == (this.state.senha)) {
            window.location = "/home";
        } else {
            if (this.state.vendedores[0].email == (this.state.email) &&
                this.state.vendedores[0].senha == (this.state.senha)) {
                window.location = "/home";
            } else {
                swal({
                    title: "Atenção!",
                    text: "Seu email ou senha estão incorretos!",
                    icon: "warning",
                    button: "OK",
                });
            }
        }
    }

    render() {
        return (
            <div>
                <NavbarInst />

                <Row className="justify-content-center m-0">
                    <Col md="6" className="box-login bg-light mx-3 p-5 rounded shadow">
                        <h4 className="mb-3">Entrar</h4>
                        <Form>
                            <Form.Group controlId="formEmail">
                                <Form.Control onInput={(e) => this.setState({ email: e.target.value })} type="email" placeholder="Email" />
                                <Form.Label className="text-muted">Digite seu endereço de E-mail.</Form.Label>
                            </Form.Group>

                            <Form.Group controlId="formSenha">
                                <Form.Control onInput={(e) => this.setState({ senha: e.target.value })} type="password" placeholder="Senha" />
                                <Form.Label className="text-muted">Digite sua senha Senha.</Form.Label>
                            </Form.Group>

                            {/* <Link className="text-light" to="/home"> */}
                            <Button variant="primary" onClick={this.login}>Entrar</Button>
                            {/* </Link> */}
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