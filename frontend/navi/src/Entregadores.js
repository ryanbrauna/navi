import React, { Component } from 'react';
// import './css/index.css';
import Avatar from '@material-ui/core/Avatar';
import swal from 'sweetalert';

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
    Form
} from 'react-bootstrap';

//Icon
import PersonIcon from '@material-ui/icons/Person';
import AddIcon from '@material-ui/icons/Add';
import CloseIcon from '@material-ui/icons/Close';

export default class Home extends Component {

    constructor(props) {
        super(props);
        this.state = this.initialState;
    }

    salvarEntregador = () => {
        swal({
            title: "Sucesso!",
            text: "Os dados do entregador foram atualizados.",
            icon: "success",
            button: "OK",
        }).then(() => { this.initialModal() });
    }

    editarEntregador = () => {
        this.setState({
            formDisabled: false,
            btnDanger: "Cancelar",
            btnPrimary: "Salvar",
            onClickBtnPrimary: this.salvarEntregador
        })
    }

    initialModal = () => {
        this.setState({
            showModal: false,
            formDisabled: true,
            btnDanger: "Excluir Entregador",
            btnPrimary: "Alterar Cadastro",
            onClickBtnPrimary: this.editarEntregador
        });
    }

    initialState = {
        showModal: false,
        formDisabled: true,
        btnDanger: "Excluir Entregador",
        btnPrimary: "Alterar Cadastro",
        onClickBtnPrimary: this.editarEntregador,
        hideFormCad: "d-none",
        nomeEntregador: "",
        emailEntregador: "",
        senhaEntregador: "",
        cpfEntregador: "",
        cnpjEntregador: ""

    }

    cadastarEntregador = e => {
        e.preventDefault();

        swal({
            title: "Sucesso!",
            text: "Entregador cadastrado.",
            icon: "success",
            button: "OK",
        }).then(() => window.location.reload());
    }

    render() {
        const { showModal, formDisabled, btnDanger, btnPrimary, onClickBtnPrimary, hideFormCad } = this.state;
        const handleShow = () => { this.setState({ showModal: true }) };
        const handleClose = () => { this.initialModal() };

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
                                            <Form.Control
                                                type="text"
                                                placeholder="Nome completo do entregador"
                                                required
                                            />
                                        </Form.Group>
                                    </Col>
                                    <Col>
                                        <Form.Group>
                                            <Form.Label className="mb-0">E-mail:</Form.Label>
                                            <Form.Control
                                                type="email"
                                                placeholder="E-mail do entregador"
                                                required
                                            />
                                        </Form.Group>
                                    </Col>
                                </Row>
                                <Row>
                                    <Col>
                                        <Form.Group>
                                            <Form.Label className="mb-0">Senha:</Form.Label>
                                            <Form.Control
                                                type="password"
                                                placeholder="Senha do entregador"
                                                required
                                            />
                                        </Form.Group>
                                    </Col>
                                    <Col>
                                        <Form.Group>
                                            <Form.Label className="mb-0">CPF:</Form.Label>
                                            <Form.Control
                                                type="text"
                                                placeholder="CPF do entregador"
                                                required
                                            />
                                        </Form.Group>
                                    </Col>
                                </Row>
                                <Row>
                                    <Col>
                                        <Form.Group>
                                            <Form.Label className="mb-0">CNH:</Form.Label>
                                            <Form.Control
                                                type="text"
                                                placeholder="CNH do entregador"
                                                required
                                            />
                                        </Form.Group>
                                    </Col>
                                    <Col>
                                        <Button className="btn-cad-entregador px-4" type="submit">Cadastrar</Button>
                                    </Col>
                                </Row>
                            </Form>
                        </Col>
                    </Row>
                </div>

                <div className="p-4" style={{margin: "0 0 0 250px"}}>
                    <Card style={{ width: '18rem' }}>
                        <Card.Body>
                            <Row>
                                <Col sm={3}>
                                    <Avatar
                                        className="avatar-entregador"
                                        src={require('./img/caminhao-de-costa.jpg')}
                                    />
                                </Col>
                                <Col>
                                    <Card.Text>
                                        <span>Fulano</span>
                                        <br />
                                        <span className="text-muted">Fulano@Tal.com</span>
                                    </Card.Text>
                                </Col>
                            </Row>
                        </Card.Body>
                        <Card.Footer >
                            <span className="span-link" onClick={handleShow}>
                                <PersonIcon className="icon" />
                                <span>Vizualizar</span>
                            </span>
                        </Card.Footer>
                    </Card>
                </div>

                <Modal show={showModal} onHide={handleClose}>
                    <Modal.Header closeButton>
                        <Modal.Title>
                            <span>Fulano</span>
                        </Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Form>
                            <Form.Group>
                                <Form.Label className="mb-0">Nome Completo:</Form.Label>
                                <Form.Control
                                    type="text"
                                    placeholder="Nome completo do entregador"
                                    value="Fulano de Tal"
                                    disabled={formDisabled}
                                />
                            </Form.Group>
                            <Form.Group>
                                <Form.Label className="mb-0">E-mail:</Form.Label>
                                <Form.Control
                                    type="email"
                                    placeholder="E-mail do entregador"
                                    value="Fulano@Tal.com"
                                    disabled={formDisabled}
                                />
                            </Form.Group>
                            <Form.Group>
                                <Form.Label className="mb-0">Senha:</Form.Label>
                                <Form.Control
                                    type="password"
                                    placeholder="Senha do entregador"
                                    value="Fulano de Tal"
                                    disabled={formDisabled}
                                />
                            </Form.Group>
                            <Form.Group>
                                <Form.Label className="mb-0">CPF:</Form.Label>
                                <Form.Control
                                    type="text"
                                    placeholder="CPF do entregador"
                                    value="123456789123"
                                    disabled={formDisabled}
                                />
                            </Form.Group>
                            <Form.Group>
                                <Form.Label className="mb-0">CNH:</Form.Label>
                                <Form.Control
                                    type="text"
                                    placeholder="CNH do entregador"
                                    value="12345678912"
                                    disabled={formDisabled}
                                />
                            </Form.Group>
                            <Form.Group>
                                <Form.Label>Imagem do entregador:</Form.Label>
                                <Avatar
                                    className="avatar-entregador-modal"
                                    src={require('./img/caminhao-de-costa.jpg')}
                                />
                            </Form.Group>
                        </Form>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="danger" onClick={handleClose}>{btnDanger}</Button>
                        <Button variant="primary" onClick={onClickBtnPrimary}>{btnPrimary}</Button>
                    </Modal.Footer>
                </Modal>
            </div>
        );
    }
}