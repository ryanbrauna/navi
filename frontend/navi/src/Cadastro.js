import React, { Component } from 'react';
import './css/Cadastro.css';
import { Link } from 'react-router-dom';

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


class Cadastro extends Component {
    render() {
        return (
            <div className="body">
                <NavbarInst />

                <Container>
                    <div className="cadastro p-5 rounded shadow">
                        <h4 className="mb-3">Cadastro</h4>
                        <Form>
                            <Row>
                                <Col sm="12">
                                    <p>Selecione o tipo de cadastro:</p>
                                </Col>
                                <Col>
                                    {['radio'].map((type) => (
                                        <div key={`inline-${type}`} className="mb-3">
                                            <Form.Check className="text-primary mr-5" inline label="Vendedor" type={type} id={`inline-${type}-1`} />
                                            <Form.Check className="text-primary mr-5" inline label="Comprador" type={type} id={`inline-${type}-2`} />
                                        </div>
                                    ))}
                                </Col>
                            </Row>

                            <Form.Row>
                                <Form.Group className="mr-4" as={Col} controlId="formNome">
                                    <Form.Control placeholder="Nome" />
                                    <Form.Label>Digite seu Nome.</Form.Label>
                                </Form.Group>

                                <Form.Group className="mr-4" as={Col} controlId="formCPF">
                                    <Form.Control placeholder="CPF" />
                                    <Form.Label>Digite seu CPF.</Form.Label>
                                </Form.Group>
                            </Form.Row>

                            <Form.Row>
                                <Form.Group className="mr-4" as={Col} controlId="formEmail">
                                    <Form.Control type="email" placeholder="Email" />
                                    <Form.Label>Digite seu endereço de E-mail.</Form.Label>
                                </Form.Group>

                                <Form.Group className="mr-4" as={Col} controlId="formTelefone">
                                    <Form.Control placeholder="Telefone" />
                                    <Form.Label>Digite seu Telefone.</Form.Label>
                                </Form.Group>
                            </Form.Row>

                            <Form.Row>
                                <Form.Group className="mr-4" as={Col} controlId="formSenha">
                                    <Form.Control type="password" placeholder="Senha" />
                                    <Form.Label>Digite uma senha Senha.</Form.Label>
                                </Form.Group>

                                <Form.Group className="mr-4" as={Col} controlId="formConfSenha">
                                    <Form.Control type="password" placeholder="Confirmação de Senha" />
                                    <Form.Label>Confirme a senha Senha.</Form.Label>
                                </Form.Group>
                            </Form.Row>

                            <Link className="text-light" to="/login">
                                <Button className="mt-3 px-5" variant="primary" type="submit">Cadastrar</Button>
                            </Link>
                        </Form>
                    </div>
                </Container>

                <div id="fim">
                    <span> NAVI © 2020</span>
                </div >
            </div>
        );
    }
}

export default Cadastro;