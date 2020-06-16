import React, { Component } from 'react';
import './css/Cadastro.css';

import $ from 'jquery';
import './jquery.mask';

// import { Link } from 'react-router-dom';
import axios from 'axios';
import swal from 'sweetalert';

// Components
import NavbarInst from './NavbarInst';

// Bootstrap
// import 'bootstrap/dist/css/bootstrap.css';
import {
    Row,
    Col,
    Container,
    Form,
    Button,
    Spinner
} from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';


class Cadastro extends Component {

    constructor(props) {
        super(props);
        this.state = this.initialState;
        this.submitCad = this.submitCad.bind(this);
        this.cadChange = this.cadChange.bind(this);
    }

    componentDidMount() {
        $('#cpf').mask('000.000.000-00', { reverse: false });
        $('#telefone').mask('(00) 00000-0000');
        $('#cnpj').mask('00.000.000/0000-00', { reverse: false });
        $('#cep').mask('00000-000');


    }


    initialState = {
        tipo: "",
        nome: "",
        cod: "",
        email: "",
        telefone: "",
        senha: "",
        confSenha: "",
        cep: "",
        logradouro: "",
        bairro: "",
        localidade: "",
        uf: "",
        numero: "",
        complemento: "",
        campoCod: "",
        nomeLoja: "",
        descLoja: "",
        show: false,
        loading: false
    }

    submitCad = e => {
        this.setState({ loading: true });
        e.preventDefault();

        if (this.state.senha == this.state.confSenha) {

            if (this.state.tipo == "C") {
                axios.post("http://navi--api.herokuapp.com/cadastro/comprador", {
                    "nome": this.state.nome,
                    "email": this.state.email,
                    "senha": this.state.senha,
                    "cpf": this.state.cod,
                    "telefone": this.state.telefone
                }).then(responseCA => {
                    if (responseCA.data != null) {
                        console.log("Dados Pessoais do Comprador cadastrado!");
                        axios.post(`http://navi--api.herokuapp.com/cadastro/comprador/${this.state.cod}/endereco`, {
                            "cep": this.state.cep,
                            "logradouro": this.state.logradouro,
                            "bairro": this.state.bairro,
                            "localidade": this.state.localidade,
                            "uf": this.state.uf,
                            "numero": this.state.numero,
                            "complememnto": this.state.complemento
                        }).then(responseCB => {
                            console.log("Endereço do Comprador cadastrado!");
                            if (responseCB.data != null) {
                                this.setState(this.initialState);
                                swal({
                                    title: "Sucesso!",
                                    text: "Você foi cadastrado com sucesso",
                                    icon: "success",
                                    button: "OK",
                                }).then(() => {
                                    window.location = "/login";
                                });
                            } else {
                                alert(responseCB);
                            }
                        });
                    } else {
                        alert(responseCA);
                    }
                });
            } else {
                axios.post("http://navi--api.herokuapp.com/cadastro/vendedor", {
                    "nome": this.state.nome,
                    "email": this.state.email,
                    "senha": this.state.senha,
                    "cnpj": this.state.cod
                }).then(responseVA => {
                    if (responseVA.data != null) {
                        console.log("Dados Pessoais do Vendedor cadastrado!");
                        axios.post(`http://navi--api.herokuapp.com/cadastro/vendedor/${this.state.cod}/loja`, {
                            "nome": this.state.nomeLoja,
                            "descricao": this.state.descLoja
                        }).then(responseVB => {
                            if (responseVB.data != null) {
                                console.log("Loja do Vendedor cadastrada!");
                                axios.post(`http://navi--api.herokuapp.com/cadastro/vendedor/${this.state.cod}/loja/endereco`, {
                                    "cep": this.state.cep,
                                    "logradouro": this.state.logradouro,
                                    "bairro": this.state.bairro,
                                    "localidade": this.state.localidade,
                                    "uf": this.state.uf,
                                    "numero": this.state.numero,
                                    "complemento": this.state.complemento
                                }).then(responseVC => {
                                    if (responseVC.data != null) {
                                        console.log("Endereço do Vendedor cadastrado!");
                                        this.setState(this.initialState);
                                        swal({
                                            title: "Sucesso!",
                                            text: "Você foi cadastrado com sucesso",
                                            icon: "success",
                                            button: "OK",
                                        }).then(() => {
                                            window.location = "/login";
                                        });
                                    } else {
                                        alert(responseVC);
                                    }
                                })
                            } else {
                                alert(responseVB);
                            }
                        })
                    } else {
                        alert(responseVA);
                    }
                });
            }
        }
        else {
            swal({
                title: "Atenção!",
                text: "Senhas não coincidem!",
                icon: "warning",
                button: "OK",
            })

            this.setState({ loading: false });

        }

    }

    cadChange = e => {
        this.setState({
            [e.target.name]: e.target.value
        });
    }

    showForm(tipo, cod) {
        this.setState({
            tipo: tipo,
            campoCod: cod,
            show: true
        });
    }

    render() {
        const {
            show,
            tipo,
            nome,
            cod,
            email,
            telefone,
            senha,
            confSenha,
            cep,
            logradouro,
            bairro,
            localidade,
            uf,
            numero,
            complemento,
            nomeLoja,
            descLoja,
            campoCod,
            loading
        } = this.state;

        return (
            <div className="body">
                <NavbarInst />

                <Container>
                    <div className="cadastro my-3 px-5 py-5 rounded shadow">
                        <h1 className="text-center mb-3 font-weight-light">Cadastrar</h1>
                        <Form onSubmit={this.submitCad} id="formCad">
                            <Row className="text-center">
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
                                            this.showForm("V", "CNPJ")
                                        }}
                                    />
                                    <Form.Check inline
                                        name="tipo"
                                        id={`tipoC`}
                                        className="text-primary mr-0"
                                        label="Comprador"
                                        type="radio"
                                        onClick={() => {
                                            this.showForm("C", "CPF")
                                        }}
                                    />
                                </Col>
                            </Row>
                            <div className="mt-3" style={{ display: show ? "block" : "none" }}>
                                <h4 className="mt-4">Dados Pessoais</h4>
                                <p className="my-2 text-danger text-atencao-cad">Os campos com " <b>*</b> " é obrigatorio o preencher.</p>
                                <Row>
                                    <Col md={6}>
                                        <Form.Group>
                                            <Form.Control required
                                                placeholder="Nome"
                                                name="nome"
                                                value={nome}
                                                onChange={this.cadChange}
                                            />
                                            <Form.Label><span className="text-danger">*</span> Digite seu Nome.</Form.Label>
                                        </Form.Group>
                                    </Col>
                                    <Col md={6}>
                                        <Form.Group>
                                            <Form.Control required
                                                id="cpf"
                                                placeholder={campoCod}
                                                name="cod"
                                                value={cod}
                                                onChange={this.cadChange}
                                                className={campoCod == "CPF" ? "" : "d-none"}
                                            />
                                            <Form.Control required
                                                id="cnpj"
                                                placeholder={campoCod}
                                                name="cod"
                                                value={cod}
                                                onChange={this.cadChange}
                                                className={campoCod == "CNPJ" ? "" : "d-none"}
                                            />
                                            <Form.Label><span className="text-danger">*</span> Digite seu {campoCod}.</Form.Label>
                                        </Form.Group>
                                    </Col>
                                </Row>
                                <Row>
                                    <Col md={6}>
                                        <Form.Group>
                                            <Form.Control required
                                                type="email"
                                                placeholder="E-mail"
                                                name="email"
                                                value={email}
                                                onChange={this.cadChange}
                                            />
                                            <Form.Label><span className="text-danger">*</span> Digite seu endereço de E-mail.</Form.Label>
                                        </Form.Group>
                                    </Col>
                                    <Col md={6}>
                                        <Form.Group>
                                            <Form.Control
                                                id="telefone"
                                                placeholder="Telefone"
                                                name="telefone"
                                                value={telefone}
                                                onChange={this.cadChange}
                                            />
                                            <Form.Label>Digite seu Telefone.</Form.Label>
                                        </Form.Group>
                                    </Col>
                                </Row>
                                <Row>
                                    <Col md={6}>
                                        <Form.Group>
                                            <Form.Control required
                                                minLength="8"
                                                type="password"
                                                placeholder="Senha"
                                                name="senha"
                                                value={senha}
                                                onChange={this.cadChange}
                                            />
                                            <Form.Label><span className="text-danger">*</span> Digite uma senha Senha.</Form.Label>
                                        </Form.Group>
                                    </Col>
                                    <Col md={6}>
                                        <Form.Group>
                                            <Form.Control required
                                                minLength="8"
                                                type="password"
                                                placeholder="Confirmação de Senha"
                                                name="confSenha"
                                                value={confSenha}
                                                onChange={this.cadChange}
                                            />
                                            <Form.Label><span className="text-danger">*</span> Confirme a senha Senha.</Form.Label>
                                        </Form.Group>
                                    </Col>
                                </Row>

                                <div style={{ display: tipo == "V" ? "block" : "none" }}>
                                    <h4 className="mt-3">Dados da Loja</h4>
                                    <p className="my-2 text-danger text-atencao-cad">Os campos com " <b>*</b> " é obrigatorio o preencher.</p>
                                    <Form.Group>
                                        <Form.Control required={tipo == "V" ? true : false}
                                            placeholder="Nome da Loja"
                                            name="nomeLoja"
                                            value={nomeLoja}
                                            onChange={this.cadChange}
                                        />
                                        <Form.Label><span className="text-danger">*</span> Digite o Nome da sua Loja.</Form.Label>
                                    </Form.Group>
                                    <Form.Group>
                                        <Form.Control required={tipo == "V" ? true : false}
                                            as="textarea"
                                            rows={2}
                                            placeholder="Descrição..."
                                            name="descLoja"
                                            value={descLoja}
                                            onChange={this.cadChange}
                                        />
                                        <Form.Label>Digite uma descrição.</Form.Label>
                                    </Form.Group>
                                </div>

                                <h4 className="mt-4">Endereço</h4>
                                <p className="my-2 text-danger text-atencao-cad">Os campos com " <b>*</b> " é obrigatorio o preencher.</p>
                                <Row className="mb-3">
                                    <Col md={4}>
                                        <Form.Group className="mb-0">
                                            <Form.Control required
                                                id="cep"
                                                placeholder="CEP"
                                                name="cep"
                                                value={cep}
                                                onChange={this.cadChange}
                                            />
                                            <Form.Label><span className="text-danger">*</span> Insira seu CEP.</Form.Label>
                                        </Form.Group>
                                    </Col>
                                    <Col>
                                        <div className="mt-3">
                                            <span className="text-muted mr-1">Esqueceu seu CEP? Vá em</span>
                                            <a href="http://www.buscacep.correios.com.br/sistemas/buscacep/" target="blank">buscar CEP...</a>
                                        </div>
                                    </Col>
                                </Row>
                                <Row>
                                    <Col sm={9}>
                                        <Form.Group>
                                            <Form.Control required
                                                placeholder="Logradouro"
                                                name="logradouro"
                                                value={logradouro}
                                                onChange={this.cadChange}
                                            />
                                            <Form.Label><span className="text-danger">*</span> Digite seu logradouro.</Form.Label>
                                        </Form.Group>
                                    </Col>
                                    <Col>
                                        <Form.Group>
                                            <Form.Control required
                                                placeholder="Nº"
                                                name="numero"
                                                value={numero}
                                                onChange={this.cadChange}
                                            />
                                            <Form.Label><span className="text-danger">*</span> Digite o Nº.</Form.Label>
                                        </Form.Group>
                                    </Col>
                                </Row>
                                <Row>
                                    <Col md={6}>
                                        <Form.Group>
                                            <Form.Control
                                                placeholder="Complemento"
                                                name="complemento"
                                                value={complemento}
                                                onChange={this.cadChange}
                                            />
                                            <Form.Label>Complemento do endereço.</Form.Label>
                                        </Form.Group>
                                    </Col>
                                    <Col md={6}>
                                        <Form.Group>
                                            <Form.Control required
                                                placeholder="Bairro"
                                                name="bairro"
                                                value={bairro}
                                                onChange={this.cadChange}
                                            />
                                            <Form.Label><span className="text-danger">*</span> Digite o bairro.</Form.Label>
                                        </Form.Group>
                                    </Col>
                                </Row>
                                <Row>
                                    <Col md={5}>
                                        <Form.Group>
                                            <Form.Control required
                                                placeholder="Cidade"
                                                name="localidade"
                                                value={localidade}
                                                onChange={this.cadChange}
                                            />
                                            <Form.Label><span className="text-danger">*</span> Digite a cidade.</Form.Label>
                                        </Form.Group>
                                    </Col>
                                    <Col md={2}>
                                        <Form.Group>
                                            <Form.Control required
                                                maxLength='2'
                                                placeholder="UF"
                                                name="uf"
                                                value={uf}
                                                onChange={this.cadChange}
                                            />
                                            <Form.Label><span className="text-danger">*</span> Digite o UF.</Form.Label>
                                        </Form.Group>
                                    </Col>
                                </Row>
                                {loading ? (
                                    <div className="text-center">
                                        <Spinner animation="border" variant="primary" />
                                        <span className="d-block text-primary">Carregando, por favor aguarde...</span>
                                    </div>
                                ) : (
                                        <Button className="mt-3 px-5" variant="primary" type="submit">Cadastrar</Button>
                                    )}
                            </div>
                        </Form>
                    </div>
                </Container>

                <div style={{ height: "64px" }}></div>
                <div id="fim" className="fixed-bottom">
                    <span> NAVI © 2020</span>
                </div >
            </div>
        );
    }
}

export default Cadastro;