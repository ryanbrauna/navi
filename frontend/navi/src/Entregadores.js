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
    Card,
    Modal,
    Button,
    Form,
    Spinner
} from 'react-bootstrap';

//Icon
import PersonIcon from '@material-ui/icons/Person';
import AddIcon from '@material-ui/icons/Add';
import CloseIcon from '@material-ui/icons/Close';

export default class Entregadores extends Component {

    constructor(props) {
        super(props);
        this.state = this.initialState;
    }

    componentDidMount() {
        axios.get(`http://navi--api.herokuapp.com/${sessionStorage.getItem('@NAVI/cod')}/entregadores`).then((data) => {
            this.setState({ listEntregadores: data.data });
            console.log(this.state.listEntregadores);
        });
    }

    salvarEntregador = () => {
        this.setState({ loading: true });

        axios.put(`https://navi--api.herokuapp.com/${sessionStorage.getItem('@NAVI/cod')}/entregadores/${this.state.cpfModal}/atualizar`, {
            "nome": this.state.nomeModal,
            "email": this.state.emailModal,
            "senha": this.state.senhaModal,
            "cpf": this.state.cpfModal,
            "cnh": this.state.cnhModal
        }).then(response => {
            if (response.data != null) {
                swal({
                    title: "Sucesso!",
                    text: "Os dados do entregador foram atualizados.",
                    icon: "success",
                    button: "OK",
                }).then(() => window.location.reload());
            }
        })
    }

    editarEntregador = () => {
        this.setState({
            formDisabled: false,
            inputSenha: "text",
            btnDanger: "Cancelar",
            btnPrimary: "Salvar",
            functionBtnPrimary: this.salvarEntregador,
            functionBtnDanger: () => {
                this.setState({
                    nomeModal: this.state.entregadorModal.nome,
                    emailModal: this.state.entregadorModal.email,
                    senhaModal: this.state.entregadorModal.senha,
                    cpfModal: this.state.entregadorModal.cpf,
                    cnhModal: this.state.entregadorModal.cnh,
                    formDisabled: true,
                    inputSenha: "password",
                    btnDanger: "Excluir Entregador",
                    btnPrimary: "Alterar Cadastro",
                    functionBtnPrimary: this.editarEntregador,
                    functionBtnDanger: this.handleClose
                });
            }
        })
    }

    initialState = {
        listEntregadores: [],
        
        // Funcionalidades
        hideFormCad: "d-none",
        loading: false,
        
        // Funcionalidades Modal
        showModal: false,
        formDisabled: true,
        inputSenha: "password",
        btnDanger: "Excluir Entregador",
        btnPrimary: "Alterar Cadastro",
        functionBtnPrimary: this.editarEntregador,
        functionBtnDanger: this.handleClose,
        
        // Form Cadastro
        nomeEntregador: "",
        emailEntregador: "",
        senhaEntregador: "",
        cpfEntregador: "",
        cnhEntregador: "",
        
        // Form Modal
        titleModal: "",
        nomeModal: "",
        emailModal: "",
        senhaModal: "",
        cpfModal: "",
        cnhModal: "",
        entregadorModal: {}
    }

    initialModal = () => {
        this.setState({
            titleModal: "",
            nomeModal: "",
            emailModal: "",
            senhaModal: "",
            cpfModal: "",
            cnhModal: "",
            showModal: false,
            formDisabled: true,
            inputSenha: "password",
            btnDanger: "Excluir Entregador",
            btnPrimary: "Alterar Cadastro",
            functionBtnPrimary: this.editarEntregador,
            functionBtnDanger: this.handleClose
        });
    }

    cadastarEntregador = e => {
        e.preventDefault();
        this.setState({ loading: true });

        axios.post(`https://navi--api.herokuapp.com/${sessionStorage.getItem('@NAVI/cod')}/entregadores/cadastro`, {
            "nome": this.state.nomeEntregador,
            "email": this.state.emailEntregador,
            "senha": this.state.senhaEntregador,
            "cpf": this.state.cpfEntregador,
            "cnh": this.state.cnhEntregador
        }).then(response => {
            if (response.data != null) {
                swal({
                    title: "Sucesso!",
                    text: "Entregador cadastrado.",
                    icon: "success",
                    button: "OK",
                }).then(() => window.location.reload());
            }
        })
    }

    inputChange = e => {
        this.setState({
            [e.target.name]: e.target.value
        });
    }

    handleShow = entregador => {
        this.setState({
            showModal: true,
            entregadorModal: entregador,
            titleModal: entregador.nome,
            nomeModal: entregador.nome,
            emailModal: entregador.email,
            senhaModal: entregador.senha,
            cpfModal: entregador.cpf,
            cnhModal: entregador.cnh
        })
    };
    handleClose = () => { this.initialModal() };

    render() {
        const {
            listEntregadores,

            // Funcionalidades
            hideFormCad,
            loading,

            // Funcionalidades Modal
            showModal,
            formDisabled,
            inputSenha,
            btnDanger,
            btnPrimary,
            functionBtnPrimary,
            functionBtnDanger,

            // Form Cadastro
            nomeEntregador,
            emailEntregador,
            senhaEntregador,
            cpfEntregador,
            cnhEntregador,

            // Form Modal
            titleModal,
            nomeModal,
            emailModal,
            senhaModal,
            cpfModal,
            cnhModal
        } = this.state;

        return (
            <div>
                <Menu />

                <div className="p-4 bg-white">
                    <Row>
                        <Col>
                            <h4 className="text-primary mb-4 font-weight-light">Entregadores</h4>
                        </Col>
                    </Row>
                    <Row>
                        <Col>
                            <div className="d-inline span-link" onClick={() => { this.setState({ hideFormCad: "" }) }}>
                                <AddIcon className="icon" />
                                <span>Cadastrar um novo entregador</span>
                            </div>
                            <div
                                className={"float-right " + hideFormCad}
                                style={{ cursor: "pointer" }}
                                onClick={() => { this.setState({ hideFormCad: "d-none" }) }}
                            >
                                <CloseIcon className="icon" />
                            </div>
                        </Col>
                    </Row>
                    <Row className={hideFormCad}>
                        <Col>
                            <Form className="mt-3" onSubmit={this.cadastarEntregador}>
                                <p className="my-2 text-danger text-atencao-cad">É necessário preencher todos os campos para realizar o cadastro.</p>
                                <Row>
                                    <Col>
                                        <Form.Group>
                                            <Form.Label className="mb-0">Nome Completo:</Form.Label>
                                            <Form.Control required
                                                placeholder="Nome completo do entregador"
                                                name="nomeEntregador"
                                                value={nomeEntregador}
                                                onChange={this.inputChange}
                                            />
                                        </Form.Group>
                                    </Col>
                                    <Col>
                                        <Form.Group>
                                            <Form.Label className="mb-0">E-mail:</Form.Label>
                                            <Form.Control required
                                                type="email"
                                                placeholder="E-mail do entregador"
                                                name="emailEntregador"
                                                value={emailEntregador}
                                                onChange={this.inputChange}
                                            />
                                        </Form.Group>
                                    </Col>
                                </Row>
                                <Row>
                                    <Col>
                                        <Form.Group>
                                            <Form.Label className="mb-0">Senha:</Form.Label>
                                            <Form.Control required
                                                type="password"
                                                placeholder="Senha do entregador"
                                                name="senhaEntregador"
                                                value={senhaEntregador}
                                                onChange={this.inputChange}
                                            />
                                        </Form.Group>
                                    </Col>
                                    <Col>
                                        <Form.Group>
                                            <Form.Label className="mb-0">CPF:</Form.Label>
                                            <Form.Control required
                                                placeholder="CPF do entregador"
                                                name="cpfEntregador"
                                                value={cpfEntregador}
                                                onChange={this.inputChange}
                                            />
                                        </Form.Group>
                                    </Col>
                                </Row>
                                <Row>
                                    <Col>
                                        <Form.Group>
                                            <Form.Label className="mb-0">CNH:</Form.Label>
                                            <Form.Control required
                                                placeholder="CNH do entregador"
                                                name="cnhEntregador"
                                                value={cnhEntregador}
                                                onChange={this.inputChange}
                                            />
                                        </Form.Group>
                                    </Col>
                                    <Col>
                                        {loading ? (
                                            <div className="btn-cad-entregador">
                                                <span className="text-primary mr-2">Carregando, por favor aguarde...</span>
                                                <Spinner animation="border" variant="primary" />
                                            </div>
                                        ) : (
                                                <Button className="btn-cad-entregador px-4" type="submit">Cadastrar</Button>
                                            )}
                                    </Col>
                                </Row>
                            </Form>
                        </Col>
                    </Row>
                </div>

                <div className="p-4" style={{ margin: "0 0 0 250px" }}>
                    <Row>
                        {listEntregadores.map(entregador => {
                            return (
                                <Col md={4}>
                                    <Card className="mb-3 shadow">
                                        <Card.Body>
                                            <Row>
                                                <Col sm={3}>
                                                    <Avatar
                                                        className="avatar-entregador mx-auto"
                                                        src={require('./img/caminhao-de-costa.jpg')}
                                                    />
                                                </Col>
                                                <Col>
                                                    <Card.Text>
                                                        <span>{entregador.nome}</span>
                                                        <br />
                                                        <span className="text-muted">{entregador.email}</span>
                                                    </Card.Text>
                                                </Col>
                                            </Row>
                                        </Card.Body>
                                        <Card.Footer >
                                            <span className="span-link" onClick={() => this.handleShow(entregador)}>
                                                <PersonIcon className="icon" />
                                                <span>Visualizar</span>
                                            </span>
                                        </Card.Footer>
                                    </Card>
                                </Col>
                            );
                        })}
                    </Row>
                </div>

                <Modal show={showModal} onHide={this.handleClose}>
                    <Modal.Header closeButton>
                        <Modal.Title>
                            <span>{titleModal}</span>
                        </Modal.Title>
                    </Modal.Header>
                    <Form>
                        <Modal.Body>
                            <Form.Group>
                                <Form.Label className="mb-0">Nome Completo:</Form.Label>
                                <Form.Control
                                    placeholder="Nome completo do entregador"
                                    name="nomeModal"
                                    value={nomeModal}
                                    onChange={this.inputChange}
                                    disabled={formDisabled}
                                />
                            </Form.Group>
                            <Form.Group>
                                <Form.Label className="mb-0">E-mail:</Form.Label>
                                <Form.Control
                                    type="email"
                                    placeholder="E-mail do entregador"
                                    name="emailModal"
                                    value={emailModal}
                                    onChange={this.inputChange}
                                    disabled={formDisabled}
                                />
                            </Form.Group>
                            <Form.Group>
                                <Form.Label className="mb-0">Senha:</Form.Label>
                                <Form.Control
                                    type={inputSenha}
                                    placeholder="Senha do entregador"
                                    name="senhaModal"
                                    value={senhaModal}
                                    onChange={this.inputChange}
                                    disabled={formDisabled}
                                />
                            </Form.Group>
                            <Form.Group>
                                <Form.Label className="mb-0">CPF:</Form.Label>
                                <Form.Control
                                    placeholder="CPF do entregador"
                                    name="cpfModal"
                                    value={cpfModal}
                                    disabled
                                />
                            </Form.Group>
                            <Form.Group>
                                <Form.Label className="mb-0">CNH:</Form.Label>
                                <Form.Control
                                    placeholder="CNH do entregador"
                                    name="cnhModal"
                                    value={cnhModal}
                                    disabled
                                />
                            </Form.Group>
                            {/* <Form.Group>
                                <Form.Label>Imagem do entregador:</Form.Label>
                                <Avatar
                                    className="avatar-entregador-modal"
                                    src={require('./img/caminhao-de-costa.jpg')}
                                />
                            </Form.Group> */}
                        </Modal.Body>
                        <Modal.Footer>
                            <Button variant="danger" onClick={functionBtnDanger}>{btnDanger}</Button>
                            <Button
                                variant="primary"
                                onClick={functionBtnPrimary}
                                disabled={loading}
                            >{loading ? (
                                <Spinner
                                    as="span"
                                    animation="border"
                                    size="sm"
                                    role="status"
                                    aria-hidden="true"
                                />
                            ) : btnPrimary}</Button>
                        </Modal.Footer>
                    </Form>
                </Modal>
            </div>
        );
    }
}