import React, { Component } from 'react';
import './css/Cadastro.css';
// import { Link } from 'react-router-dom';

// Components
import NavbarInst from './NavbarInst';

// Bootstrap
// import 'bootstrap/dist/css/bootstrap.css';
import {
    Row,
    Col,
    Container,
    Form,
    Button
} from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';


class Cadastro extends Component {

    constructor(props) {
        super(props);
        this.state = this.initialState;
        this.cadChange = this.cadChange.bind(this);
        this.submitCad = this.submitCad.bind(this);
    }

    initialState = {
        tipo: "",
        nome: "",
        CPF: "",
        email: "",
        telefone: "",
        senha: "",
        confSenha: "",
        show: false
    }

    submitCad = event => {
        alert(
            "Tipo:" + this.state.tipo +
            "\nNome:" + this.state.nome +
            "\nCPF:" + this.state.CPF +
            "\nEmail:" + this.state.email +
            "\nTelefone:" + this.state.telefone +
            "\nSenha:" + this.state.senha +
            "\nConfSenha:" + this.state.confSenha
        );
    }

    cadChange = event => {
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    showForm(tipo) {
        this.setState({
            tipo: tipo,
            show: true
        });
    }

    render() {
        const { nome, CPF, email, telefone, senha, confSenha } = this.state;

        return (
            <div className="body">
                <NavbarInst />

                <Container>
                    <div className="cadastro px-5 py-3 rounded shadow">
                        <h4 className="mb-3">Cadastro</h4>
                        <Form onSubmit={this.submitCad} id="formCad">
                            <Row>
                                <Col sm="12">
                                    <p>Selecione o tipo de cadastro:</p>
                                </Col>
                                <Col>
                                    <Form.Check inline
                                        name="tipo"
                                        id={`tipoV`}
                                        className="text-primary mr-5"
                                        label="Vendedor"
                                        type="radio"
                                        onClick={() => {
                                            this.showForm("V")
                                        }}
                                    />
                                    <Form.Check inline
                                        name="tipo"
                                        id={`tipoC`}
                                        className="text-primary mr-5"
                                        label="Comprador"
                                        type="radio"
                                        onClick={() => {
                                            this.showForm("C")
                                        }}
                                    />
                                </Col>
                            </Row>
                            <div style={{ display: + this.state.show ? "block" : "none" }}>
                                <Form.Row>
                                    <Form.Group className="mr-4" as={Col} controlId="formNome">
                                        <Form.Control required
                                            placeholder="Nome"
                                            name="nome"
                                            value={nome}
                                            onChange={this.cadChange}
                                        />
                                        <Form.Label>Digite seu Nome.</Form.Label>
                                    </Form.Group>

                                    <Form.Group className="mr-4" as={Col} controlId="formCPF">
                                        <Form.Control required
                                            placeholder="CPF"
                                            name="CPF"
                                            value={CPF}
                                            onChange={this.cadChange}
                                        />
                                        <Form.Label>Digite seu CPF.</Form.Label>
                                    </Form.Group>
                                </Form.Row>

                                <Form.Row>
                                    <Form.Group className="mr-4" as={Col} controlId="formEmail">
                                        <Form.Control required
                                            type="email"
                                            placeholder="Email"
                                            name="email"
                                            value={email}
                                            onChange={this.cadChange}
                                        />
                                        <Form.Label>Digite seu endereço de E-mail.</Form.Label>
                                    </Form.Group>

                                    <Form.Group className="mr-4" as={Col} controlId="formTelefone">
                                        <Form.Control
                                            placeholder="Telefone"
                                            name="telefone"
                                            value={telefone}
                                            onChange={this.cadChange}
                                        />
                                        <Form.Label>Digite seu Telefone.</Form.Label>
                                    </Form.Group>
                                </Form.Row>

                                <Form.Row>
                                    <Form.Group className="mr-4" as={Col} controlId="formSenha">
                                        <Form.Control required
                                            type="password"
                                            placeholder="Senha"
                                            name="senha"
                                            value={senha}
                                            onChange={this.cadChange}
                                        />
                                        <Form.Label>Digite uma senha Senha.</Form.Label>
                                    </Form.Group>

                                    <Form.Group className="mr-4" as={Col} controlId="formConfSenha">
                                        <Form.Control required
                                            type="password"
                                            placeholder="Confirmação de Senha"
                                            name="confSenha"
                                            value={confSenha}
                                            onChange={this.cadChange}
                                        />
                                        <Form.Label>Confirme a senha Senha.</Form.Label>
                                    </Form.Group>
                                </Form.Row>

                                {/* <Link className="text-light" to="/login"> */}
                                <Button className="mt-3 px-5" variant="primary" type="submit">Cadastrar</Button>
                                {/* </Link> */}
                            </div>
                        </Form>
                    </div>
                </Container>

                <div id="fim" className="fixed-bottom">
                    <span> NAVI © 2020</span>
                </div >
            </div>
        );
    }
}

export default Cadastro;