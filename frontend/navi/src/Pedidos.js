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
    Table
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

    handleShow = pedido => {
        this.setState({
            showModal: true,
            pedidoModal: pedido
        });
    };
    handleClose = () => { this.initialModal() };

    footerComprador = pedido => {
        return (
            <span className="span-link" onClick={() => this.handleShow(pedido)}>
                <RoomIcon className="icon" />
                <span>Acompanhar entrega</span>
            </span>
        );
    }

    footerVendedor = pedido => {
        return (
            <span className="span-link" onClick={() => this.handleShow(pedido)}>
                <ReceiptIcon className="icon" />
                <span>Ver Pedido</span>
            </span>
        );
    }

    footerEntregador = pedido => {
        return (
            <span className="span-link" onClick={() => this.handleShow(pedido)}>
                <RoomIcon className="icon" />
                <span>Ver local de entrega</span>
            </span>
        );
    }

    modalEdit = (showModal, formDisabled, btnDanger, btnPrimary, onClickBtnPrimary, pedido) => {
        return (
            <Modal show={showModal} onHide={this.handleClose} size="lg">
                <Modal.Header closeButton>
                    <Modal.Title>
                        <span>Pedido: {pedido.numeroDoPedido}</span>
                    </Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Row>
                        <Col>
                            <Form.Group className="mb-1">
                                <Form.Label className="mb-0">Status do pedido:</Form.Label>
                                <Form.Control as="select" custom disabled={formDisabled}>
                                    <option>{pedido.estado}</option>
                                    <option>Em Andamento</option>
                                    <option>Entregue</option>
                                    <option>Cancelado</option>
                                </Form.Control>
                            </Form.Group>
                            <Form.Group>
                                <Form.Control
                                    placeholder="Nome do Entregador"
                                    value={pedido.entregador == null ? "Nenhum" : pedido.entregador}
                                    disabled
                                />
                                <Form.Label>Entregador</Form.Label>
                            </Form.Group>
                        </Col>
                    </Row>
                    <Row>
                        <Col>
                            <Table striped size="sm">
                                <thead className="bg-dark text-light">
                                    <tr>
                                        <th colSpan="2">Dados do Pedido</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Numero do pedido:</td>
                                        <td>{pedido.numeroDoPedido}</td>
                                    </tr>
                                    <tr>
                                        <td>Descrição:</td>
                                        <td>{pedido.descricao}</td>
                                    </tr>
                                    <tr>
                                        <td>Anotação:</td>
                                        <td>{pedido.anotacoes}</td>
                                    </tr>
                                    <tr>
                                        <td>Preço:</td>
                                        <td>R$ {pedido.preco != null ? pedido.preco.toFixed(2) : ""}</td>
                                    </tr>
                                </tbody>
                                <thead className="bg-dark text-light">
                                    <tr>
                                        <th colSpan="2">Dados do Comprador</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Nome:</td>
                                        <td>{pedido.comprador != null ? pedido.comprador.nome : null}</td>
                                    </tr>
                                    <tr>
                                        <td>E-mail:</td>
                                        <td>{pedido.comprador != null ? pedido.comprador.email : null}</td>
                                    </tr>
                                    <tr>
                                        <td>CPF:</td>
                                        <td>{pedido.comprador != null ? pedido.comprador.cpf : null}</td>
                                    </tr>
                                    <tr>
                                        <td>Telefone:</td>
                                        <td>{pedido.comprador != null ? pedido.comprador.telefone != null ? pedido.comprador.telefone : (<i>Não registrado</i>) : null}</td>
                                    </tr>
                                    <tr>
                                        <td>Endereço:</td>
                                        {pedido.comprador != null ? (
                                            <td>{pedido.comprador.endereco.logradouro}, {pedido.comprador.endereco.numero}, {pedido.comprador.endereco.bairro}, {pedido.comprador.endereco.localidade} - {pedido.comprador.endereco.uf}</td>
                                        ) : null}
                                    </tr>
                                    <tr>
                                        <td>Complemento:</td>
                                        <td>{pedido.comprador != null ? pedido.comprador.endereco.complememnto != null ? pedido.comprador.endereco.complememnto : (<i>Nenhum</i>) : null}</td>
                                    </tr>
                                    <tr>
                                        <td>CEP:</td>
                                        <td>{pedido.comprador != null ? pedido.comprador.endereco.cep : null}</td>
                                    </tr>
                                </tbody>
                            </Table>
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
        const {
            listPedidos,
            pedidoModal,
            showModal,
            formDisabled,
            btnDanger,
            btnPrimary,
            onClickBtnPrimary,
            hideFormCad
        } = this.state;

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
                                                placeholder="Numero do Pedido"
                                                required
                                            />
                                        </Form.Group>
                                        <Row>
                                            <Col>
                                                <Form.Group>
                                                    <Form.Label className="mb-0"><span className="text-danger">*</span> CPF:</Form.Label>
                                                    <Form.Control
                                                        placeholder="CPF do Comprador"
                                                        required
                                                    />
                                                </Form.Group>
                                            </Col>
                                            <Col>
                                                <Form.Group>
                                                    <Form.Label className="mb-0"><span className="text-danger">*</span> Preço:</Form.Label>
                                                    <Form.Control
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

                <div className="p-4" style={{ margin: "0 0 0 250px" }}>
                    <Row>
                        {listPedidos.map(pedido => {
                            return (
                                <Col lg={4}>
                                    <Card className="mb-3 shadow">
                                        <Card.Img variant="top" src={require('./img/caminhao-de-costa.jpg')} />
                                        <Card.Body>
                                            <Card.Title className="text-primary">Pedido: {pedido.numeroDoPedido}</Card.Title>
                                            <Card.Subtitle className="mb-3 text-muted">
                                                <i>Comprador: {pedido.comprador.nome}</i>
                                            </Card.Subtitle>
                                            <Card.Text>
                                                <div className="mb-1 desc-pedido">
                                                    <b>Descrição: </b>
                                                    <span>{pedido.descricao}</span>
                                                </div>
                                                <div className="mb-1">
                                                    <b>Entregador: </b>
                                                    <span>{pedido.entregador == null ? "Nenhum" : pedido.entregador}</span>
                                                </div>
                                                <div>
                                                    <b>Preço: </b>
                                                    <span className="text-primary">R$ {pedido.preco.toFixed(2)}</span>
                                                </div>
                                            </Card.Text>
                                        </Card.Body>
                                        <Card.Footer >
                                            {sessionStorage.getItem('@NAVI/tipo') == "Comprador" ? this.footerComprador(pedido) : sessionStorage.getItem('@NAVI/tipo') == "Vendedor" ? this.footerVendedor(pedido) : this.footerEntregador(pedido)}
                                        </Card.Footer>
                                    </Card>
                                </Col>
                            );
                        })}
                    </Row>
                </div>

                {this.modalEdit(showModal, formDisabled, btnDanger, btnPrimary, onClickBtnPrimary, pedidoModal)}
            </div>
        );
    }
}