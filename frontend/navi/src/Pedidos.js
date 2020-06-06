import React, { Component } from 'react';
import { Link } from 'react-router-dom';
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
    Image
} from 'react-bootstrap';

//Icon
import RoomIcon from '@material-ui/icons/Room';
import AddIcon from '@material-ui/icons/Add';
import CloseIcon from '@material-ui/icons/Close';
// import InfoIcon from '@material-ui/icons/Info';
import ReceiptIcon from '@material-ui/icons/Receipt';

export default class Home extends Component {

    constructor(props) {
        super(props);
        this.state = this.initialState;
    }

    componentDidMount() {
        axios.get(`http://navi--api.herokuapp.com/vendedor/${sessionStorage.getItem('@NAVI/cod')}/pedidos`).then((data) => {
            this.setState({ listPedidos: data.data });
            console.log(this.state.listPedidos);
        });
    }

    salvarPedido = () => {
        swal({
            title: "Sucesso!",
            text: "O status do pedido foi atualizado.",
            icon: "success",
            button: "OK",
        }).then(() => { this.initialModal() });
    }

    editarPedido = () => {
        this.setState({
            formDisabled: false,
            btnDanger: "Cancelar",
            btnPrimary: "Salvar",
            onClickBtnPrimary: this.salvarPedido
        })
    }

    initialModal = () => {
        this.setState({
            showModal: false,
            formDisabled: true,
            btnDanger: "Excluir Pedido",
            btnPrimary: "Alterar Status",
            onClickBtnPrimary: this.editarPedido
        });
    }

    initialState = {
        listPedidos: [],
        pedidoModal: {},
        showModal: false,
        formDisabled: true,
        btnDanger: "Excluir Pedido",
        btnPrimary: "Alterar Status",
        onClickBtnPrimary: this.editarPedido,
        hideFormCad: "d-none",
        numeroPedido: "",
        precoPedido: "",
        descPedido: "",
        anotacaoPedido: ""
    }

    cadastarPedido = e => {
        e.preventDefault();

        swal({
            title: "Sucesso!",
            text: "Pedido registrado.",
            icon: "success",
            button: "OK",
        }).then(() => window.location.reload());
    }

    handleShow = () => { this.setState({ showModal: true }) };
    handleClose = () => { this.initialModal() };

    footerComprador = () => {
        return (
            <span className="span-link" onClick={this.handleShow}>
                <RoomIcon className="icon" />
                <span>Acompanhar entrega</span>
            </span>
        );
    }

    footerVendedor = () => {
        return (
            <span className="span-link" onClick={this.handleShow}>
                <ReceiptIcon className="icon" />
                <span>Ver Pedido</span>
            </span>
        );
    }

    footerEntregador = () => {
        return (
            <span className="span-link" onClick={this.handleShow}>
                <RoomIcon className="icon" />
                <span>Ver local de entrega</span>
            </span>
        );
    }

    modalEdit = (showModal, formDisabled, btnDanger, btnPrimary, onClickBtnPrimary) => {
        return (
            <Modal show={showModal} onHide={this.handleClose} size="xl">
                <Modal.Header closeButton>
                    <Modal.Title>
                        <span>Nº do Pedido</span>
                    </Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Row>
                        <Col>
                            <Image
                                src={require('./img/maps.png')}
                                className="m-3 w-100"
                                alt="Maps"
                            />
                        </Col>
                        <Col>
                            <Form>
                                <Form.Group>
                                    <Form.Label className="mb-0">Status do pedido:</Form.Label>
                                    <Form.Control as="select" custom disabled={formDisabled}>
                                        <option>Registrado</option>
                                        <option>Em Andamento</option>
                                        <option>Entregue</option>
                                        <option>Cancelado</option>
                                    </Form.Control>
                                </Form.Group>
                                <Form.Group>
                                    <Form.Label className="mb-0">Numero do Pedido:</Form.Label>
                                    <Form.Control
                                        type="text"
                                        placeholder="Nome do pedido"
                                        value="XPTO1234"
                                        disabled
                                    />
                                </Form.Group>
                                <Form.Group>
                                    <Form.Label className="mb-0">CPF:</Form.Label>
                                    <Form.Control
                                        type="text"
                                        placeholder="CPF do Comprador"
                                        value="393.092.658-09"
                                        disabled
                                    />
                                </Form.Group>
                                <Form.Group>
                                    <Form.Label className="mb-0">Preço:</Form.Label>
                                    <Form.Control
                                        type="texto"
                                        placeholder="R$ 00,00"
                                        value="Fulano@Tal.com"
                                        disabled
                                    />
                                </Form.Group>
                                <Form.Group>
                                    <Form.Label className="mb-0">Descrição:</Form.Label>
                                    <Form.Control
                                        as="textarea"
                                        placeholder="Descrição do Pedido..."
                                        rows={2}
                                        value="Essa é a descrição do pedido, decrito para descrever o pedido descrevendo descrito"
                                        disabled
                                    />
                                </Form.Group>
                                <Form.Group>
                                    <Form.Label className="mb-0">Anotação:</Form.Label>
                                    <Form.Control
                                        as="textarea"
                                        placeholder="Anotação do Pedido..."
                                        rows={2}
                                        disabled
                                    />
                                </Form.Group>
                            </Form>
                        </Col>
                    </Row>
                </Modal.Body>
                {sessionStorage.getItem('@NAVI/tipo') == "Vendedor" ? (
                    <Modal.Footer>
                        <Button variant="danger" onClick={this.handleClose}>{btnDanger}</Button>
                        <Button variant="primary" onClick={onClickBtnPrimary}>{btnPrimary}</Button>
                    </Modal.Footer>
                ) : ""}
            </Modal>
        );
    }

    render() {
        const { showModal, formDisabled, btnDanger, btnPrimary, onClickBtnPrimary, hideFormCad } = this.state;

        return (
            <div>
                <Menu />

                <div className="p-4 bg-white">
                    <Row>
                        <Col>
                            <h4 className="text-primary font-weight-light mb-0">Pedidos</h4>
                        </Col>
                    </Row>
                    {sessionStorage.getItem('@NAVI/tipo') == "Vendedor" ? (
                        <div>
                            <Row className="mt-3">
                                <Col>
                                    <div className="d-inline span-link" onClick={() => { this.setState({ hideFormCad: "" }) }}>
                                        <AddIcon className="icon" />
                                        <span>Registrar um novo pedido</span>
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
                                    <Form className="mt-3" onSubmit={this.cadastarPedido}>
                                        <p className="my-2 text-danger text-atencao-cad">É necessário preencher todos os campos com " * " para realizar o registro.</p>
                                        <Form.Group>
                                            <Form.Label className="mb-0"><span className="text-danger">*</span> Numero do Pedido:</Form.Label>
                                            <Form.Control
                                                type="text"
                                                placeholder="Numero do Pedido"
                                                required
                                            />
                                        </Form.Group>
                                        <Row>
                                            <Col>
                                                <Form.Group>
                                                    <Form.Label className="mb-0"><span className="text-danger">*</span> CPF:</Form.Label>
                                                    <Form.Control
                                                        type="text"
                                                        placeholder="CPF do Comprador"
                                                        required
                                                    />
                                                </Form.Group>
                                            </Col>
                                            <Col>
                                                <Form.Group>
                                                    <Form.Label className="mb-0"><span className="text-danger">*</span> Preço:</Form.Label>
                                                    <Form.Control
                                                        type="text"
                                                        placeholder="R$ 00,00"
                                                        required
                                                    />
                                                </Form.Group>
                                            </Col>
                                        </Row>
                                        <Row>
                                            <Col>
                                                <Form.Group>
                                                    <Form.Label className="mb-0"><span className="text-danger">*</span> Descrição:</Form.Label>
                                                    <Form.Control
                                                        as="textarea"
                                                        placeholder="Descrição do Pedido..."
                                                        rows={3}
                                                        required
                                                    />
                                                </Form.Group>
                                            </Col>
                                            <Col>
                                                <Form.Group>
                                                    <Form.Label className="mb-0">Anotação:</Form.Label>
                                                    <Form.Control
                                                        as="textarea"
                                                        placeholder="Anotação do Pedido..."
                                                        rows={3}
                                                    />
                                                </Form.Group>
                                            </Col>
                                        </Row>
                                        <Row>
                                            <Col>
                                                <Button className="float-right px-4" type="submit">Registrar</Button>
                                            </Col>
                                        </Row>
                                    </Form>
                                </Col>
                            </Row>
                        </div>
                    ) : ""}
                </div>

                <div className="p-4" style={{margin: "0 0 0 250px"}}>
                    <Row>
                        <Col>
                            <Card style={{ width: '18rem' }}>
                                <Card.Img variant="top" src={require('./img/caminhao-de-costa.jpg')} />
                                <Card.Body>
                                    <Card.Title className="text-primary">Revestimento</Card.Title>
                                    <Card.Text>
                                        <span>Joli Material de Construção</span>
                                        <br />
                                        <span className="text-muted">(11) 99876-5432</span>
                                    </Card.Text>
                                </Card.Body>
                                <Card.Footer >
                                    {sessionStorage.getItem('@NAVI/tipo') == "Comprador" ? this.footerComprador() : sessionStorage.getItem('@NAVI/tipo') == "Vendedor" ? this.footerVendedor() : this.footerEntregador()}
                                </Card.Footer>
                            </Card>
                        </Col>
                    </Row>
                </div>

                {this.modalEdit(showModal, formDisabled, btnDanger, btnPrimary, onClickBtnPrimary)}
            </div>
        );
    }
}