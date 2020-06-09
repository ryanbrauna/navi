import React, { Component } from 'react';
import Avatar from '@material-ui/core/Avatar';
import swal from 'sweetalert';
import axios from 'axios';

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

export default class Perfil extends Component {

    constructor(props) {
        super(props);
        this.state = this.initialState;
    }

    initialState = {
        user: {},
        loading: false,
        formDisabled: true
    }

    componentDidMount() {
        if (sessionStorage.getItem('@NAVI/tipo') == "Comprador") {
            axios.get(`http://navi--api.herokuapp.com/comprador/${sessionStorage.getItem('@NAVI/cod')}`).then((data) => {
                this.setState({ user: data.data });
                console.log(this.state.user);
            });
        } else if (sessionStorage.getItem('@NAVI/tipo') == "Vendedor") {
            axios.get(`http://navi--api.herokuapp.com/vendedor/${sessionStorage.getItem('@NAVI/cod')}`).then((data) => {
                this.setState({ user: data.data });
                console.log(this.state.user);
            });
        } else {
            axios.get(`http://navi--api.herokuapp.com/${sessionStorage.getItem('@NAVI/loja')}/entregadores/${sessionStorage.getItem('@NAVI/cod')}`).then((data) => {
                this.setState({ user: data.data });
                console.log(this.state.user);
            });
        }
    }

    salvarPerfil = () => {
        swal({
            title: "Sucesso!",
            text: "Os seus dados foram atualizados.",
            icon: "success",
            button: "OK",
        }).then(() => window.location.reload());
    }

    render() {
        const {
            user,
            loading,
            formDisabled
        } = this.state;

        return (
            <div>
                <Menu />

                <div className="py-4 px-3 bg-white">
                    <Row>
                        <Col sm={10}>
                            <h4 className="text-primary font-weight-light mb-0">{formDisabled ? "" : "Editando "}Perfil</h4>
                        </Col>
                        <Col sm={2}>
                            {sessionStorage.getItem('@NAVI/tipo') != "Entregador" ? formDisabled ? (
                                <div className="text-primary float-right edit-perfil" onClick={() => this.setState({ formDisabled: false })}>
                                    <EditIcon className="icon" />
                                    <span>Editar Perfil</span>
                                </div>
                            ) : (
                                    <div className="text-danger float-right edit-perfil" onClick={() => this.setState({ formDisabled: true })}>
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
                                                name="nome"
                                                value={user.nome}
                                                // onChange={this.cadChange}
                                                disabled={formDisabled}
                                            />
                                            <Form.Label className="mb-0">Nome</Form.Label>
                                        </Form.Group>
                                        <Form.Group>
                                            <Form.Control
                                                name="email"
                                                value={user.email}
                                                // onChange={this.cadChange}
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
                                                    // onChange={this.cadChange}
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
                                                        name="telefone"
                                                        value={user.telefone}
                                                        // onChange={this.cadChange}
                                                        disabled={formDisabled}
                                                    />
                                                    <Form.Label className="mb-0">Telefone</Form.Label>
                                                </Form.Group>
                                            ) : (
                                                    <Form.Group>
                                                        <Form.Control
                                                            name="cnh"
                                                            value={user.cnh}
                                                            // onChange={this.cadChange}
                                                            disabled={formDisabled}
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
                                                        name="senha"
                                                        value={user.senha}
                                                        // onChange={this.cadChange}
                                                        disabled={formDisabled}
                                                    />
                                                    <Form.Label className="mb-0">Senha</Form.Label>
                                                </Form.Group>
                                            </Col>
                                            <Col md={6}>
                                                <Form.Group>
                                                    <Form.Control
                                                        type="password"
                                                        name="senha"
                                                        value={user.senha}
                                                        // onChange={this.cadChange}
                                                        disabled={formDisabled}
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
                                                    name="nomeLoja"
                                                    // value={nomeLoja}
                                                    // onChange={this.cadChange}
                                                    disabled={formDisabled}
                                                />
                                                <Form.Label className="mb-0 text-primary">Nome da Loja</Form.Label>
                                            </Form.Group>
                                            <Form.Group controlId="formGroupDesc">
                                                <Form.Control
                                                    placeholder="Não há descrição"
                                                    as="textarea"
                                                    rows={2}
                                                    name="descLoja"
                                                    // value={descLoja}
                                                    // onChange={this.cadChange}
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
                                                            placeholder="CEP"
                                                            name="cep"
                                                            // value={cep}
                                                            // onChange={this.cadChange}
                                                            disabled={formDisabled}
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
                                                            name="logradouro"
                                                            // value={logradouro}
                                                            // onChange={this.cadChange}
                                                            disabled={formDisabled}
                                                        />
                                                        <Form.Label className="mb-0 text-primary">Logradouro</Form.Label>
                                                    </Form.Group>
                                                </Col>
                                                <Col>
                                                    <Form.Group controlId="formGroupNumero">
                                                        <Form.Control
                                                            placeholder="Nº"
                                                            name="numero"
                                                            // value={numero}
                                                            // onChange={this.cadChange}
                                                            disabled={formDisabled}
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
                                                            name="complemento"
                                                            // value={complemento}
                                                            // onChange={this.cadChange}
                                                            disabled={formDisabled}
                                                        />
                                                        <Form.Label className="mb-0 text-primary">Complemento do endereço</Form.Label>
                                                    </Form.Group>
                                                </Col>
                                                <Col md={6}>
                                                    <Form.Group controlId="formGroupBairro">
                                                        <Form.Control
                                                            placeholder="Bairro"
                                                            name="bairro"
                                                            // value={bairro}
                                                            // onChange={this.cadChange}
                                                            disabled={formDisabled}
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
                                                            name="localidade"
                                                            // value={localidade}
                                                            // onChange={this.cadChange}
                                                            disabled={formDisabled}
                                                        />
                                                        <Form.Label className="mb-0 text-primary">Cidade</Form.Label>
                                                    </Form.Group>
                                                </Col>
                                                <Col md={2}>
                                                    <Form.Group controlId="formGroupUf">
                                                        <Form.Control
                                                            placeholder="UF"
                                                            name="uf"
                                                            // value={uf}
                                                            // onChange={this.cadChange}
                                                            disabled={formDisabled}
                                                        />
                                                        <Form.Label className="mb-0 text-primary">UF</Form.Label>
                                                    </Form.Group>
                                                </Col>
                                            </Row>
                                        </div>
                                    ) : ""}
                                    {formDisabled ? "" : loading ? (
                                        <div className="text-center">
                                            <Spinner animation="border" variant="primary" />
                                            <span className="d-block text-primary">Carregando, por favor aguarde...</span>
                                        </div>
                                    ) : (
                                            <Button className="mt-3 px-5" variant="primary" onClick={this.salvarPerfil}>Salvar</Button>
                                        )}
                                </Container>
                            </div>
                        </Col>
                    </Row>
                </div>
            </div>
        )
    }
}