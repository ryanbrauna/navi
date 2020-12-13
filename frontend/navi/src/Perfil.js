import React, { Component } from 'react';
import Avatar from '@material-ui/core/Avatar';
import swal from 'sweetalert';
import axios from 'axios';


import $ from 'jquery';
import './jquery.mask';

// Components
import Menu from './Menu';

// Bootstrap
import 'bootstrap/dist/css/bootstrap.css';
import {
    Row,
    Col,
    Button,
    Form,
    Spinner,
    Container
} from 'react-bootstrap';

import CloseIcon from '@material-ui/icons/Close';
import EditIcon from '@material-ui/icons/Edit';
import CheckIcon from '@material-ui/icons/Check';

export default class Perfil extends Component {

    constructor(props) {
        super(props);
        this.state = this.initialState;
    }

    initialState = {
        //Dados usuario
        user: {},
        userName: "",
        userEmail: "",
        userTelefone: "",
        userSenha: "",
        userConfSenha: "",

        //Endereco usuario
        endereco: {},
        enderecoCep: "",
        enderecoLogradouro: "",
        enderecoNumero: "",
        enderecoCompemento: "",
        enderecoBairro: "",
        enderecoCidade: "",
        enderecoUf: "",


        loja: {},
        lojaNome: "",
        lojaDesc: "",

        loading: false,
        formDisabled: true
    }

    componentDidMount() {
        if (sessionStorage.getItem('@NAVI/tipo') == "Comprador") {
            axios.get(`https://navi--api.herokuapp.com/comprador/${sessionStorage.getItem('@NAVI/cod')}`).then((data) => {
                this.setState({
                    // Dados pessoais
                    user: data.data,
                    userName: data.data.nome,
                    userEmail: data.data.email,
                    userTelefone: data.data.telefone,
                    userSenha: data.data.senha,
                    userConfSenha: data.data.senha,

                    // Endereco
                    endereco: data.data.endereco,
                    enderecoCep: data.data.endereco.cep,
                    enderecoLogradouro: data.data.endereco.logradouro,
                    enderecoNumero: data.data.endereco.numero,
                    enderecoCompemento: data.data.endereco.complememnto,
                    enderecoBairro: data.data.endereco.bairro,
                    enderecoCidade: data.data.endereco.localidade,
                    enderecoUf: data.data.endereco.uf,

                    loja: data.data,
                });
                console.log(this.state.user);
                console.log(this.state.endereco);
            });
        } else if (sessionStorage.getItem('@NAVI/tipo') == "Vendedor") {
            axios.get(`https://navi--api.herokuapp.com/vendedor/${sessionStorage.getItem('@NAVI/cod')}/lojas`).then((data) => {
                this.setState({
                    // Dados pessoais
                    user: data.data.vendedor,
                    userName: data.data.vendedor.nome,
                    userEmail: data.data.vendedor.email,
                    userTelefone: data.data.vendedor.telefone,
                    userSenha: data.data.vendedor.senha,
                    userConfSenha: data.data.vendedor.senha,

                    loja: data.data,
                    lojaNome: data.data.nome,
                    lojaDesc: data.data.descricao,

                    // Endereco
                    endereco: data.data.endereco,
                    enderecoCep: data.data.endereco.cep,
                    enderecoLogradouro: data.data.endereco.logradouro,
                    enderecoNumero: data.data.endereco.numero,
                    enderecoCompemento: data.data.endereco.complememnto,
                    enderecoBairro: data.data.endereco.bairro,
                    enderecoCidade: data.data.endereco.localidade,
                    enderecoUf: data.data.endereco.uf
                });
                console.log(this.state.user);
                console.log(this.state.endereco);
                console.log(this.state.loja);
            });
        } else {
            axios.get(`https://navi--api.herokuapp.com/${sessionStorage.getItem('@NAVI/loja')}/entregadores/${sessionStorage.getItem('@NAVI/cod')}`).then((data) => {
                this.setState({
                    user: data.data,
                    userName: data.data.nome,
                    userEmail: data.data.email
                });
                console.log(this.state.user);
            });
        }

        $('#cep').mask('00000-000');
    }

    initialForm = (user, endereco, loja) => {
        this.setState({
            formDisabled: true,
            userName: user.nome,
            userEmail: user.email,
            userTelefone: user.telefone,
            userSenha: user.senha,
            userConfSenha: user.senha,

            enderecoCep: endereco.cep,
            enderecoLogradouro: endereco.logradouro,
            enderecoNumero: endereco.numero,
            enderecoCompemento: endereco.complememnto,
            enderecoBairro: endereco.bairro,
            enderecoCidade: endereco.localidade,
            enderecoUf: endereco.uf,

            lojaNome: loja.nome,
            lojaDesc: loja.descricao
        })
    }

    salvarPerfil = () => {
        var link = sessionStorage.getItem('@NAVI/tipo') == "Comprador" ? (
            `https://navi--api.herokuapp.com/comprador/${sessionStorage.getItem('@NAVI/cod')}/atualizar`
        ) : (
                `https://navi--api.herokuapp.com/vendedor/${sessionStorage.getItem('@NAVI/cod')}`
            );

        var body = sessionStorage.getItem('@NAVI/tipo') == "Comprador" ? {
            "nome": this.state.userName,
            "email": this.state.userEmail,
            "senha": this.state.userSenha,
            "telefone": this.state.userTelefone,
            "cpf": this.state.user.cpf,
            "endereco": this.state.endereco
        } : {
                "nome": this.state.userName,
                "email": this.state.userEmail,
                "senha": this.state.userSenha,
                "telefone": this.state.userTelefone,
                "cnpj": this.state.user.cnpj
            };

        axios.put(link, body).then(response => {
            link = "";
            body = {};
            if (response.data != null) {
                if (sessionStorage.getItem('@NAVI/tipo') == "Comprador") {
                    link = `https://navi--api.herokuapp.com/${this.state.endereco.id}`;
                    body = {
                        "cep": this.state.enderecoCep,
                        "logradouro": this.state.enderecoLogradouro,
                        "bairro": this.state.enderecoBairro,
                        "localidade": this.state.enderecoCidade,
                        "uf": this.state.enderecoUf,
                        "numero": this.state.enderecoNumero,
                        "complememnto": this.state.enderecoCompemento
                    };
                } else {
                    link = `https://navi--api.herokuapp.com/vendedor/${sessionStorage.getItem('@NAVI/cod')}/loja`;
                    body = {
                        "nome": this.state.lojaNome,
                        "descricao": this.state.lojaDesc,
                        "endereco": this.state.endereco
                    };
                }
                axios.put(link, body).then(response => {
                    if (response.data != null) {
                        swal({
                            title: "Sucesso!",
                            text: "Os seus dados foram atualizados.",
                            icon: "success",
                            button: "OK"
                        }).then(() => window.location.reload());
                    }
                });
            }
        })
    }

    inputChange = e => {
        this.setState({
            [e.target.name]: e.target.value
        });
    }

    render() {
        const {
            //Dados pessoais
            user,
            userName,
            userEmail,
            userTelefone,
            userSenha,
            userConfSenha,

            //Dados do endereco
            endereco,
            enderecoCep,
            enderecoLogradouro,
            enderecoNumero,
            enderecoCompemento,
            enderecoBairro,
            enderecoCidade,
            enderecoUf,

            loja,
            lojaNome,
            lojaDesc,

            loading,
            formDisabled
        } = this.state;

        return (
            <div>
                <Menu />

                <div className="py-4 px-3 bg-white">
                    <Row>
                        <Col sm={8}>
                            <h4 className="text-primary font-weight-light mb-0">{formDisabled ? "" : "Editando "}Perfil</h4>
                        </Col>
                        <Col sm={4}>
                            {formDisabled ? "" : loading ? (
                                <div className="text-center">
                                    <Spinner animation="border" variant="primary" />
                                    <span className="d-block text-primary">Carregando, por favor aguarde...</span>
                                </div>
                            ) : (
                                    <div className="ml-4 text-primary float-right edit-perfil" onClick={this.salvarPerfil}>
                                        <CheckIcon className="icon" />
                                        <span>Salvar</span>
                                    </div>
                                )}
                            {sessionStorage.getItem('@NAVI/tipo') != "Entregador" ? formDisabled ? (
                                <div className="text-primary float-right edit-perfil" onClick={() => this.setState({ formDisabled: false })}>
                                    <EditIcon className="icon" />
                                    <span>Editar Perfil</span>
                                </div>
                            ) : (
                                    <div className="text-danger float-right edit-perfil" onClick={() => this.initialForm(user, endereco, loja)}>
                                        <CloseIcon className="icon" />
                                        <span>Cancelar</span>
                                    </div>
                                ) : ""}
                        </Col>
                    </Row>
                </div>

                <div className="p-4" style={{ margin: "0 0 0 250px" }}>
                    <Row>
                        <Col>
                            <div className="p-4 rounded bg-white">
                                <Row>
                                    <Col md={2}>
                                        <Avatar
                                            className="d-block avatar-perfil mx-auto my-4"
                                            src={require('./img/caminhao-style.jpg')}
                                        />
                                    </Col>
                                    <Col md={10}>
                                        <Form.Group>
                                            <Form.Control
                                                name="userName"
                                                value={userName}
                                                onChange={this.inputChange}
                                                disabled={formDisabled}
                                            />
                                            <Form.Label className="mb-0">Nome</Form.Label>
                                        </Form.Group>
                                        <Form.Group>
                                            <Form.Control
                                                name="userEmail"
                                                value={userEmail}
                                                onChange={this.inputChange}
                                                disabled={formDisabled}
                                            />
                                            <Form.Label className="mb-0">E-mail</Form.Label>
                                        </Form.Group>
                                    </Col>
                                </Row>
                                <Container>
                                    <Row>
                                        <Col md={6}>
                                            <Form.Group>
                                                <Form.Control
                                                    name="cod"
                                                    value={sessionStorage.getItem('@NAVI/tipo') == "Vendedor" ? user.cnpj : user.cpf}
                                                    disabled
                                                />
                                                <Form.Label className="mb-0">{sessionStorage.getItem('@NAVI/tipo') == "Vendedor" ? "CNPJ" : "CPF"}</Form.Label>
                                            </Form.Group>
                                        </Col>
                                        <Col md={6}>
                                            {sessionStorage.getItem('@NAVI/tipo') != "Entregador" ? (
                                                <Form.Group>
                                                    <Form.Control
                                                        placeholder="Nenhum Registrado"
                                                        name="userTelefone"
                                                        value={userTelefone}
                                                        onChange={this.inputChange}
                                                        disabled={formDisabled}
                                                    />
                                                    <Form.Label className="mb-0">Telefone</Form.Label>
                                                </Form.Group>
                                            ) : (
                                                    <Form.Group>
                                                        <Form.Control
                                                            name="cnh"
                                                            value={user.cnh}
                                                            disabled
                                                        />
                                                        <Form.Label className="mb-0">CNH</Form.Label>
                                                    </Form.Group>

                                                )}
                                        </Col>
                                    </Row>
                                    {formDisabled ? "" : (
                                        <Row>
                                            <Col md={6}>
                                                <Form.Group>
                                                    <Form.Control
                                                        type="password"
                                                        name="userSenha"
                                                        value={userSenha}
                                                        onChange={this.inputChange}
                                                    />
                                                    <Form.Label className="mb-0">Senha</Form.Label>
                                                </Form.Group>
                                            </Col>
                                            <Col md={6}>
                                                <Form.Group>
                                                    <Form.Control
                                                        type="password"
                                                        name="userConfSenha"
                                                        value={userConfSenha}
                                                        onChange={this.inputChange}
                                                    />
                                                    <Form.Label className="mb-0">Confirmar senha</Form.Label>
                                                </Form.Group>
                                            </Col>
                                        </Row>
                                    )}
                                    {sessionStorage.getItem('@NAVI/tipo') == "Vendedor" ? (
                                        <div>
                                            <h4 className="mt-4 text-dark font-weight-light">Loja</h4>
                                            <Form.Group controlId="formGroupNomeLoja">
                                                <Form.Control
                                                    name="lojaNome"
                                                    value={lojaNome}
                                                    onChange={this.inputChange}
                                                    disabled={formDisabled}
                                                />
                                                <Form.Label className="mb-0 text-primary">Nome da Loja</Form.Label>
                                            </Form.Group>
                                            <Form.Group controlId="formGroupDesc">
                                                <Form.Control
                                                    placeholder="Não há descrição"
                                                    as="textarea"
                                                    rows={2}
                                                    name="lojaDesc"
                                                    value={lojaDesc}
                                                    onChange={this.inputChange}
                                                    disabled={formDisabled}
                                                />
                                                <Form.Label className="mb-0 text-primary">Descrição</Form.Label>
                                            </Form.Group>
                                        </div>
                                    ) : ""}
                                    {sessionStorage.getItem('@NAVI/tipo') != "Entregador" ? (
                                        <div>
                                            <h4 className="mt-4 text-dark font-weight-light">Endereço</h4>
                                            <Row className="mb-3">
                                                <Col md={4}>
                                                    <Form.Group className="mb-0" controlId="formGroupCep">
                                                        <Form.Control
                                                            id="cep"
                                                            placeholder="CEP"
                                                            name="enderecoCep"
                                                            value={enderecoCep}
                                                            onChange={this.inputChange}
                                                            disabled={sessionStorage.getItem('@NAVI/tipo') == "Vendedor" ? true : formDisabled}
                                                        />
                                                        <Form.Label className="mb-0 text-primary">CEP</Form.Label>
                                                    </Form.Group>
                                                </Col>
                                                {/* <Col>
                                        <div className="mt-3">
                                            <span className="text-muted mr-1">Esqueceu CEP? Vá em</span>
                                            <a href="http://www.buscacep.correios.com.br/sistemas/buscacep/" target="blank">buscar CEP...</a>
                                        </div>
                                    </Col> */}
                                            </Row>
                                            <Row>
                                                <Col sm={9}>
                                                    <Form.Group controlId="formGroupLogradouro">
                                                        <Form.Control
                                                            placeholder="Logradouro"
                                                            name="enderecoLogradouro"
                                                            value={enderecoLogradouro}
                                                            onChange={this.inputChange}
                                                            disabled={sessionStorage.getItem('@NAVI/tipo') == "Vendedor" ? true : formDisabled}
                                                        />
                                                        <Form.Label className="mb-0 text-primary">Logradouro</Form.Label>
                                                    </Form.Group>
                                                </Col>
                                                <Col>
                                                    <Form.Group controlId="formGroupNumero">
                                                        <Form.Control
                                                            placeholder="Nº"
                                                            name="enderecoNumero"
                                                            value={enderecoNumero}
                                                            onChange={this.inputChange}
                                                            disabled={sessionStorage.getItem('@NAVI/tipo') == "Vendedor" ? true : formDisabled}
                                                        />
                                                        <Form.Label className="mb-0 text-primary">Nº</Form.Label>
                                                    </Form.Group>
                                                </Col>
                                            </Row>
                                            <Row>
                                                <Col md={6}>
                                                    <Form.Group controlId="formGroupComplemento">
                                                        <Form.Control
                                                            placeholder="Nenhum"
                                                            name="enderecoCompemento"
                                                            value={enderecoCompemento}
                                                            onChange={this.inputChange}
                                                            disabled={sessionStorage.getItem('@NAVI/tipo') == "Vendedor" ? true : formDisabled}
                                                        />
                                                        <Form.Label className="mb-0 text-primary">Complemento do endereço</Form.Label>
                                                    </Form.Group>
                                                </Col>
                                                <Col md={6}>
                                                    <Form.Group controlId="formGroupBairro">
                                                        <Form.Control
                                                            placeholder="Bairro"
                                                            name="enderecoBairro"
                                                            value={enderecoBairro}
                                                            onChange={this.inputChange}
                                                            disabled={sessionStorage.getItem('@NAVI/tipo') == "Vendedor" ? true : formDisabled}
                                                        />
                                                        <Form.Label className="mb-0 text-primary">Bairro</Form.Label>
                                                    </Form.Group>
                                                </Col>
                                            </Row>
                                            <Row>
                                                <Col md={5}>
                                                    <Form.Group controlId="formGroupCidade">
                                                        <Form.Control
                                                            placeholder="Cidade"
                                                            name="enderecoCidade"
                                                            value={enderecoCidade}
                                                            onChange={this.inputChange}
                                                            disabled={sessionStorage.getItem('@NAVI/tipo') == "Vendedor" ? true : formDisabled}
                                                        />
                                                        <Form.Label className="mb-0 text-primary">Cidade</Form.Label>
                                                    </Form.Group>
                                                </Col>
                                                <Col md={2}>
                                                    <Form.Group controlId="formGroupUf">
                                                        <Form.Control
                                                            placeholder="UF"
                                                            name="enderecoUf"
                                                            value={enderecoUf}
                                                            onChange={this.inputChange}
                                                            disabled={sessionStorage.getItem('@NAVI/tipo') == "Vendedor" ? true : formDisabled}
                                                        />
                                                        <Form.Label className="mb-0 text-primary">UF</Form.Label>
                                                    </Form.Group>
                                                </Col>
                                            </Row>
                                        </div>
                                    ) : ""}
                                </Container>
                            </div>
                        </Col>
                    </Row>
                </div>
            </div>
        )
    }
}