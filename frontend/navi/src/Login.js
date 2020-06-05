import React, { Component } from 'react';
import './css/Login.css'
import { Link } from 'react-router-dom';
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
            entregadores: [],
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
        axios.get("http://localhost:8080/entregadores").then((data) => {
            this.setState({ entregadores: data.data })
        });
    }

    login() {
        var autenticador = true;
        debugger;

        for (let i = 0; i < this.state.compradores.length; i++) {
            if (this.state.compradores[i].email == (this.state.email) &&
                this.state.compradores[i].senha == (this.state.senha)) {
                sessionStorage.setItem('@NAVI/nome', this.state.compradores[i].nome);
                sessionStorage.setItem('@NAVI/cod', this.state.compradores[i].cpf);
                sessionStorage.setItem('@NAVI/tipo', "Comprador");
                window.location = "/home";
                autenticador = true;
                break;
            } else {
                autenticador = false;
            }
        }

        if (!autenticador) {
            for (let i = 0; i < this.state.vendedores.length; i++) {
                if (this.state.vendedores[i].email == (this.state.email) &&
                    this.state.vendedores[i].senha == (this.state.senha)) {
                    sessionStorage.setItem('@NAVI/nome', this.state.vendedores[i].nome);
                    sessionStorage.setItem('@NAVI/cod', this.state.vendedores[i].cnpj);
                    sessionStorage.setItem('@NAVI/tipo', "Vendedor");
                    window.location = "/home";
                    autenticador = true;
                    break;
                } else {
                    autenticador = false;
                }
            }
        }

        if (!autenticador) {
            for (let i = 0; i < this.state.entregadores.length; i++) {
                if (this.state.entregadores[i].email == (this.state.email) &&
                    this.state.entregadores[i].senha == (this.state.senha)) {
                    sessionStorage.setItem('@NAVI/nome', this.state.entregadores[i].nome);
                    sessionStorage.setItem('@NAVI/cod', this.state.entregadores[i].cpf);
                    sessionStorage.setItem('@NAVI/tipo', "Entregador");
                    window.location = "/home";
                    autenticador = true;
                    break;
                } else {
                    autenticador = false;
                }
            }
        }

        if (!autenticador) {
            swal({
                title: "Atenção!",
                text: "Seu email ou senha estão incorretos!",
                icon: "warning",
                button: "OK",
            });
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

                            <Button variant="primary w-25" onClick={this.login}>Entrar</Button>
                            <div className="text-center mt-3">
                                <span className="text-muted">Não possui uma conta no NAVI?</span>
                                <br />
                                <Link to="/cadastro">Fazer Cadastro</Link>
                            </div>
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