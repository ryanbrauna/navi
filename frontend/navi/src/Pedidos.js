import React, { Component } from 'react';
import ReactDOM from 'react-dom';
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
    Card,
    Modal,
    Button,
    Form,
    Table,
    Spinner,
    Image
} from 'react-bootstrap';

//Icon
import RoomIcon from '@material-ui/icons/Room';
import AddIcon from '@material-ui/icons/Add';
import CloseIcon from '@material-ui/icons/Close';
// import InfoIcon from '@material-ui/icons/Info';
import ReceiptIcon from '@material-ui/icons/Receipt';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import ChevronRightIcon from '@material-ui/icons/ChevronRight';

export default class Pedidos extends Component {

    constructor(props) {
        super(props);
        this.state = this.initialState;
    }

    componentDidMount() {
        if (sessionStorage.getItem('@NAVI/tipo') == "Comprador") {
            axios.get(`https://navi--api.herokuapp.com/comprador/${sessionStorage.getItem('@NAVI/cod')}/pedidos`).then((data) => {
                this.setState({ listPedidos: data.data });
                console.log(this.state.listPedidos);
            });
        } else if (sessionStorage.getItem('@NAVI/tipo') == "Vendedor") {
            axios.get(`https://navi--api.herokuapp.com/vendedor/${sessionStorage.getItem('@NAVI/cod')}/pedidos`).then((data) => {
                this.setState({ listPedidos: data.data });
                console.log(this.state.listPedidos);
            });
            axios.get(`https://navi--api.herokuapp.com/${sessionStorage.getItem('@NAVI/cod')}/entregadores`).then((data) => {
                this.setState({ listEntregadores: data.data });
                console.log(this.state.listEntregadores);
            });
        } else {
            axios.get(`https://navi--api.herokuapp.com/vendedor/${sessionStorage.getItem('@NAVI/loja')}/pedidos`).then((data) => {
                this.setState({ listPedidos: data.data });
                console.log(this.state.listPedidos);
            });
        }
        $('#cpf').mask('000.000.000-00', { reverse: false });
    }

    salvarPedido = () => {
        this.setState({ loading: true });
        var cpfDoEntregador = "";
        for (let i = 0; i < this.state.listEntregadores.length; i++) {
            if (this.state.listEntregadores[i].nome == this.state.entregadorPedido) {
                cpfDoEntregador = this.state.listEntregadores[i].cpf;
            }
        }
        axios.put(`https://navi--api.herokuapp.com/vendedor/${sessionStorage.getItem('@NAVI/cod')}/pedidos/${this.state.pedidoModal.id}?estado=${this.state.statusModal == "" ? this.state.pedidoModal.estado : this.state.statusModal}`).then(response => {
            if (response.data != null) {
                axios.put(`https://navi--api.herokuapp.com/entregador/${cpfDoEntregador}/pedidos/${this.state.pedidoModal.id}`);
                axios.post(`https://navi--api.herokuapp.com/enviar/${this.state.pedidoModal.comprador.cpf}/${this.state.pedidoModal.numeroDoPedido}`).then(r => console.log("SMS enviado:" + r));
                swal({
                    title: "Sucesso!",
                    text: "O status do pedido foi atualizado.",
                    icon: "success",
                    button: "OK",
                }).then(() => window.location.reload());
            }
        });
    }

    editarPedido = () => {
        this.setState({
            formDisabled: false,
            btnDanger: "Cancelar",
            btnPrimary: "Salvar",
            onClickBtnPrimary: this.salvarPedido
        })
    }

    excluirPedido = (nrPedido, id) => {
        swal({
            title: "Você tem certeza?",
            text: "Uma vez excluido, não podera ser recuperado!",
            icon: "warning",
            buttons: ["Cancelar", true]
        }).then((willDelete) => {
            if (willDelete) {
                axios.delete(`https://navi--api.herokuapp.com/pedidos/${id}`).then(response => {
                    if (response) {
                        swal({
                            title: "Sucesso!",
                            text: `O pedido ${nrPedido} foi excluido.`,
                            icon: "success",
                            button: "OK",
                        }).then(() => window.location.reload());
                    }
                });
            }
        });
    }

    initialModal = () => {
        this.setState({
            loading: false,
            showModal: false,
            formDisabled: true,
            btnDanger: "Excluir Pedido",
            btnPrimary: "Alterar Status",
            onClickBtnPrimary: this.editarPedido
        });
    }

    initialState = {
        listPedidos: [],
        listEntregadores: [],
        pedidoModal: {},
        loading: false,
        showModal: false,
        formDisabled: true,
        onClickBtnPrimary: this.editarPedido,
        btnDanger: "Excluir Pedido",
        btnPrimary: "Editar Pedido",
        hideFormCad: "d-none",
        cpfComprador: "",
        precoPedido: "",
        descPedido: "",
        anotacaoPedido: "",
        statusModal: "",
        entregadorPedido: "",
        hideA: false,
        hideB: false,
        hideC: true,
        hideD: true
    }

    registrarPedido = e => {
        e.preventDefault();
        this.setState({ loading: true });

        axios.post(`https://navi--api.herokuapp.com/vendedor/${sessionStorage.getItem('@NAVI/cod')}/pedidos/registrar?cpf=${this.state.cpfComprador}`, {
            "descricao": this.state.descPedido,
            "preco": this.state.precoPedido,
            "anotacoes": this.state.anotacaoPedido
        }).then(response => {
            if (response.data != null) {
                swal({
                    title: "Sucesso!",
                    text: "Pedido registrado.",
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

    handleShow = pedido => {
        this.setState({
            showModal: true,
            pedidoModal: pedido
        });
    };
    handleClose = () => { this.initialModal() };

    footerComprador = pedido => {
        return (
            <div>
                <a className="span-link mb-2 d-block text-decoration-none" target="_blank" href={`https://guilherme-mendes.outsystemscloud.com/GoogleMapsDemo/MultipleWaypointDirections.aspx`}>
                    <RoomIcon className="icon" />
                    <span>Acompanhar entrega</span>
                </a>
                <span className="span-link" onClick={() => this.handleShow(pedido)}>
                    <ReceiptIcon className="icon" />
                    <span>Ver Pedido</span>
                </span>
            </div>
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
            <div>
                <a className="span-link mb-2 d-block text-decoration-none" target="_blank" href={`https://guilherme-mendes.outsystemscloud.com/GoogleMapsDemo/HomeEntry.aspx?cpf=${pedido.comprador.cpf}&cnpj=${pedido.loja.vendedor.cnpj}`}>
                    <RoomIcon className="icon" />
                    <span>Ver local de entrega</span>
                </a>
                <span className="span-link" onClick={() => this.handleShow(pedido)}>
                    <ReceiptIcon className="icon" />
                    <span>Ver Pedido</span>
                </span>
            </div>
        );
    }

    modalEdit = (showModal, formDisabled, btnDanger, btnPrimary, onClickBtnPrimary, pedido) => {
        // this.setState({statusModal: pedido != null ? pedido.estado : ""});
        const cpfEntregador = pedido.entregador == null ? "" : pedido.entregador.cpf;
        var dadosComprador = (
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
                    <td>Telefone:</td>
                    <td>{pedido.comprador != null ? pedido.comprador.telefone != "" ? pedido.comprador.telefone : (<i>Não registrado</i>) : null}</td>
                </tr>
                <tr>
                    <td>Endereço:</td>
                    {pedido.comprador != null ? (
                        <td>{pedido.comprador.endereco.logradouro}, {pedido.comprador.endereco.numero}, {pedido.comprador.endereco.bairro}, {pedido.comprador.endereco.localidade} - {pedido.comprador.endereco.uf}</td>
                    ) : null}
                </tr>
                <tr>
                    <td>Complemento:</td>
                    <td>{pedido.comprador != null ? pedido.comprador.endereco.complememnto != "" ? pedido.comprador.endereco.complememnto : (<i>Nenhum</i>) : null}</td>
                </tr>
                <tr>
                    <td>CEP:</td>
                    <td>{pedido.comprador != null ? pedido.comprador.endereco.cep : null}</td>
                </tr>
            </tbody>
        );
        var dadosLoja = (
            <tbody>
                <tr>
                    <td>Nome:</td>
                    <td>{pedido.loja != null ? pedido.loja.nome : null}</td>
                </tr>
                {/* <tr>
                    <td>Descrição:</td>
                    <td>{pedido.loja != null ? pedido.loja.descricao : null}</td>
                </tr> */}
                <tr>
                    <td>Endereço:</td>
                    {pedido.loja != null ? (
                        <td>{pedido.loja.endereco.logradouro}, {pedido.loja.endereco.numero}, {pedido.loja.endereco.bairro}, {pedido.loja.endereco.localidade} - {pedido.loja.endereco.uf}</td>
                    ) : null}
                </tr>
                <tr>
                    <td>CEP:</td>
                    <td>{pedido.loja != null ? pedido.loja.endereco.cep : null}</td>
                </tr>
                <tr>
                    <td>Nome do Vendedor:</td>
                    <td>{pedido.loja != null ? pedido.loja.vendedor.nome : null}</td>
                </tr>
                <tr>
                    <td>E-mail do Vendedor:</td>
                    <td>{pedido.loja != null ? pedido.loja.vendedor.email : null}</td>
                </tr>
                <tr>
                    <td>Telefone do Vendedor:</td>
                    <td>{pedido.loja != null ? pedido.loja.vendedor.telefone != "" ? pedido.loja.vendedor.telefone : (<i>Não registrado</i>) : null}</td>
                </tr>
            </tbody>
        );

        var groupButtonsComprador = (
            <Row className="justify-content-end">
                <Col md={4}>
                    <Button className="w-100" variant="info" size="sm">
                        <a
                            className="text-white text-decoration-none"
                            href={`https://navi--api.herokuapp.com/${pedido.loja == null ? "" : pedido.loja.vendedor.cnpj}/pedido/${pedido.id}`}
                            download
                        >Baixar Arquivo do Pedido</a>
                    </Button>
                </Col>
            </Row>
        )

        var groupButtonsVendedor = (
            <Row className="justify-content-end">
                <Col md={3}>
                    <Button
                        className="w-100"
                        variant="danger"
                        onClick={formDisabled ? () => this.excluirPedido(pedido.numeroDoPedido, pedido.id) : this.handleClose}
                        size="sm"
                    >{btnDanger}</Button>
                </Col>
                <Col md={4}>
                    <Button className="w-100" variant="info" size="sm">
                        <a
                            className="text-white text-decoration-none"
                            href={`http://navi--api.herokuapp.com/${pedido.loja == null ? "" : pedido.loja.vendedor.cnpj}/pedido/${pedido.id}`}
                            download
                        >Baixar Arquivo do Pedido</a>
                    </Button>
                </Col>
                <Col md={3}>
                    <Button
                        className="w-100"
                        variant="primary"
                        onClick={onClickBtnPrimary}
                        disabled={this.state.loading}
                        size="sm"
                    >{this.state.loading ? (
                        <Spinner
                            as="span"
                            animation="border"
                            size="sm"
                            role="status"
                            aria-hidden="true"
                        />
                    ) : btnPrimary}</Button>
                </Col>
            </Row>
        )

        var groupButtonsEntregador = (
            <Row className="justify-content-end">
                {cpfEntregador != sessionStorage.getItem('@NAVI/cod') ? "" : pedido.estado == "Em Andamento" ? (
                    <Col md={3}>
                        <Button
                            className="w-100"
                            variant="info"
                            onClick={() => {
                                this.setState({ loading: true });
                                axios.put(`https://navi--api.herokuapp.com/vendedor/${pedido.loja.vendedor.cnpj}/pedidos/${pedido.id}?estado=Entregue`).then(response => {
                                    if (response.data != null) {
                                        axios.post(`https://navi--api.herokuapp.com/enviar/${this.state.pedidoModal.comprador.cpf}/${this.state.pedidoModal.numeroDoPedido}`).then(r => console.log("SMS enviado:" + r));
                                        swal({
                                            title: "Sucesso!",
                                            text: "O status do pedido foi atualizado.",
                                            icon: "success",
                                            button: "OK",
                                        }).then(() => window.location.reload());
                                    }
                                });
                            }}
                            disabled={this.state.loading}
                            size="sm"
                        >{this.state.loading ? (
                            <Spinner
                                as="span"
                                animation="border"
                                size="sm"
                                role="status"
                                aria-hidden="true"
                            />
                        ) : "Confirmar Entrega"}</Button>
                    </Col>
                ) : ""}
                {pedido.entregador != null ? "" : (
                    <Col md={3}>
                        <Button
                            className="w-100"
                            variant="info"
                            onClick={() => {
                                this.setState({ loading: true });
                                axios.put(`https://navi--api.herokuapp.com/entregador/${sessionStorage.getItem('@NAVI/cod')}/pedidos/${pedido.id}`).then(response => {
                                    if (response.data != null) {
                                        swal({
                                            title: "Sucesso!",
                                            text: "O entregador do pedido foi atualizado.",
                                            icon: "success",
                                            button: "OK",
                                        }).then(() => window.location.reload());
                                    }
                                });
                            }}
                            disabled={this.state.loading}
                            size="sm"
                        >{this.state.loading ? (
                            <Spinner
                                as="span"
                                animation="border"
                                size="sm"
                                role="status"
                                aria-hidden="true"
                            />
                        ) : "Realizar Entrega"}</Button>
                    </Col>
                )}
            </Row>
        )

        return (
            <Modal show={showModal} onHide={this.handleClose} size="lg">
                <Modal.Header closeButton>
                    <Modal.Title id="topoModal">
                        <span>Pedido: {pedido.numeroDoPedido}</span>
                    </Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    {sessionStorage.getItem('@NAVI/tipo') == "Vendedor" ? groupButtonsVendedor : sessionStorage.getItem('@NAVI/tipo') == "Entregador" ? groupButtonsEntregador : pedido.entregador != null ? groupButtonsComprador : ""}
                    <Row>
                        <Col>
                            <Form.Group className="mb-1">
                                <Form.Label className="mb-0">Status do pedido:</Form.Label>
                                <Form.Control custom
                                    as="select"
                                    name="statusModal"
                                    onChange={this.inputChange}
                                    disabled={formDisabled}
                                >
                                    <option disabled>{pedido.estado}</option>
                                    <option>Em Andamento</option>
                                    <option>Entregue</option>
                                    <option>Cancelado</option>
                                </Form.Control>
                            </Form.Group>
                            <Form.Group>
                                <Form.Label className="mb-0">Entregador:</Form.Label>
                                <Form.Control
                                    as="select"
                                    name="entregadorPedido"
                                    onChange={this.inputChange}
                                    disabled={formDisabled}
                                >
                                    <option>{pedido.entregador == null ? "Nenhum" : pedido.entregador.nome}</option>
                                    {this.state.listEntregadores.sort(function (a, b) {
                                        return (a.numeroDoPedido > b.numeroDoPedido) ? 1 : ((b.numeroDoPedido > a.numeroDoPedido) ? -1 : 0);
                                    }).map(entregador => {
                                        return (
                                            <option>{entregador.nome}</option>
                                        );
                                    })}
                                </Form.Control>
                            </Form.Group>
                        </Col>
                    </Row>
                    <Row>
                        <Col>
                            <Table striped size="sm">
                                <thead className="bg-info text-light">
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
                                <thead className="bg-info text-light">
                                    <tr>
                                        <th colSpan="2">Dados {sessionStorage.getItem('@NAVI/tipo') == "Comprador" ? "da Loja" : "do Comprador"}</th>
                                    </tr>
                                </thead>
                                {sessionStorage.getItem('@NAVI/tipo') == "Comprador" ? dadosLoja : dadosComprador}
                            </Table>
                        </Col>
                    </Row>
                </Modal.Body>

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
            hideFormCad,
            cpfComprador,
            precoPedido,
            descPedido,
            anotacaoPedido,
            loading,
            hideA,
            hideB,
            hideC,
            hideD
        } = this.state;

        return (
            <div>
                <Menu />

                <div className="p-4 bg-white border-bottom">
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
                                        className={"float-right text-danger " + hideFormCad}
                                        style={{ cursor: "pointer" }}
                                        onClick={() => { this.setState({ hideFormCad: "d-none" }) }}
                                    >
                                        <span>Fechar</span>
                                        <CloseIcon className="icon" />
                                    </div>
                                </Col>
                            </Row>
                            <Row className={hideFormCad}>
                                <Col>
                                    <Form className="mt-3" onSubmit={this.registrarPedido}>
                                        <p className="my-2 text-danger text-atencao-cad">É necessário preencher todos os campos com " <b>*</b> " para realizar o registro.</p>
                                        <Row>
                                            <Col>
                                                <Form.Group>
                                                    <Form.Label className="mb-0"><span className="text-danger">*</span> CPF:</Form.Label>
                                                    <Form.Control required
                                                        id="cpf"
                                                        placeholder="CPF do Comprador"
                                                        name="cpfComprador"
                                                        value={cpfComprador}
                                                        onChange={this.inputChange}
                                                    />
                                                    <span className="text-danger text-atencao-cad">O CPF do comprador deve estar cadastrado no nosso sistema</span>
                                                </Form.Group>
                                            </Col>
                                            <Col>
                                                <Form.Group>
                                                    <Form.Label className="mb-0"><span className="text-danger">*</span> Preço:</Form.Label>
                                                    <Form.Control required
                                                        type="number"
                                                        placeholder="00.00"
                                                        name="precoPedido"
                                                        value={precoPedido}
                                                        onChange={this.inputChange}
                                                    />
                                                    <span className="text-danger text-atencao-cad">Por favor separe as casas decimais com "." (ex.: 1199.99)</span>
                                                </Form.Group>
                                            </Col>
                                        </Row>
                                        <Row>
                                            <Col>
                                                <Form.Group>
                                                    <Form.Label className="mb-0"><span className="text-danger">*</span> Descrição:</Form.Label>
                                                    <Form.Control required
                                                        as="textarea"
                                                        placeholder="Descrição do Pedido..."
                                                        rows={3}
                                                        name="descPedido"
                                                        value={descPedido}
                                                        onChange={this.inputChange}
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
                                                        name="anotacaoPedido"
                                                        value={anotacaoPedido}
                                                        onChange={this.inputChange}
                                                    />
                                                </Form.Group>
                                            </Col>
                                        </Row>
                                        <Row>
                                            <Col>
                                                {loading ? (
                                                    <div className="float-right">
                                                        <span className="text-primary mr-2">Carregando, por favor aguarde...</span>
                                                        <Spinner animation="border" variant="primary" />
                                                    </div>
                                                ) : (
                                                        <Button className="float-right px-4" type="submit">Registrar</Button>
                                                    )}
                                            </Col>
                                        </Row>
                                    </Form>
                                </Col>
                            </Row>
                        </div>
                    ) : ""}
                </div>

                <div
                    className="px-3 py-2 bg-white text-primary border-bottom"
                    style={{
                        margin: "0 0 0 250px",
                        cursor: "pointer"
                    }}
                    onClick={() => {
                        if (hideA) {
                            this.setState({
                                hideA: false
                            });
                        } else {
                            this.setState({
                                hideA: true
                            });
                        }
                    }}
                >
                    {hideA ? <ChevronRightIcon className="icon" /> : <ExpandMoreIcon className="icon" />}
                    <h5 className="m-0 d-inline">Pedido Registrado</h5>
                </div>
                <div className="p-3" style={{ margin: "0 0 0 250px", display: hideA ? "none" : "block" }}>
                    <Row>
                        {listPedidos.sort(function (a, b) {
                            return (a.numeroDoPedido > b.numeroDoPedido) ? 1 : ((b.numeroDoPedido > a.numeroDoPedido) ? -1 : 0);
                        }).map(pedido => {
                            if (pedido.estado == "Pedido Registrado") {
                                return (
                                    <Col lg={3}>
                                        <Card className="mb-3 shadow">
                                            <div className="w-100 bg-primary rounded-top" style={{ height: "50px" }}>
                                                <Image
                                                    src={require('./img/navi-logo-white.png')}
                                                    width="40"
                                                    height="40"
                                                    className="ml-2 mt-1"
                                                />
                                            </div>
                                            <Card.Body>
                                                <Card.Title className="text-primary">Pedido: {pedido.numeroDoPedido}</Card.Title>
                                                <Card.Subtitle className="mb-3 text-muted desc-pedido">
                                                    <i>{sessionStorage.getItem('@NAVI/tipo') == "Comprador" ? "Loja: " + pedido.loja.nome : "Comprador: " + pedido.comprador.nome}</i>
                                                </Card.Subtitle>
                                                <Card.Text>
                                                    <div className="mb-1 desc-pedido">
                                                        <b>Descrição: </b>
                                                        <span>{pedido.descricao}</span>
                                                    </div>
                                                    <div className="mb-1  desc-pedido">
                                                        <b>Entregador: </b>
                                                        <span>{pedido.entregador == null ? "Nenhum" : pedido.entregador.nome}</span>
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
                            }
                        })}
                    </Row>
                </div>
                <div
                    className="px-3 py-2 bg-white text-primary border-bottom"
                    style={{
                        margin: "0 0 0 250px",
                        cursor: "pointer"
                    }}
                    onClick={() => {
                        if (hideB) {
                            this.setState({
                                hideB: false
                            });
                        } else {
                            this.setState({
                                hideB: true
                            });
                        }
                    }}
                >
                    {hideB ? <ChevronRightIcon className="icon" /> : <ExpandMoreIcon className="icon" />}
                    <h5 className="m-0 d-inline">Em Andamento</h5>
                </div>
                <div className="p-3" style={{ margin: "0 0 0 250px", display: hideB ? "none" : "block" }}>
                    <Row>
                        {listPedidos.sort(function (a, b) {
                            return (a.numeroDoPedido > b.numeroDoPedido) ? 1 : ((b.numeroDoPedido > a.numeroDoPedido) ? -1 : 0);
                        }).map(pedido => {
                            if (pedido.estado == "Em Andamento") {
                                return (
                                    <Col lg={3}>
                                        <Card className="mb-3 shadow">
                                            <div className="w-100 bg-primary rounded-top" style={{ height: "50px" }}>
                                                <Image
                                                    src={require('./img/navi-logo-white.png')}
                                                    width="40"
                                                    height="40"
                                                    className="ml-2 mt-1"
                                                />
                                            </div>
                                            <Card.Body>
                                                <Card.Title className="text-primary">Pedido: {pedido.numeroDoPedido}</Card.Title>
                                                <Card.Subtitle className="mb-3 text-muted desc-pedido">
                                                    <i>{sessionStorage.getItem('@NAVI/tipo') == "Comprador" ? "Loja: " + pedido.loja.nome : "Comprador: " + pedido.comprador.nome}</i>
                                                </Card.Subtitle>
                                                <Card.Text>
                                                    <div className="mb-1 desc-pedido">
                                                        <b>Descrição: </b>
                                                        <span>{pedido.descricao}</span>
                                                    </div>
                                                    <div className="mb-1  desc-pedido">
                                                        <b>Entregador: </b>
                                                        <span>{pedido.entregador == null ? "Nenhum" : pedido.entregador.nome}</span>
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
                            }
                        })}
                    </Row>
                </div>
                <div
                    className="px-3 py-2 bg-white text-primary border-bottom"
                    style={{
                        margin: "0 0 0 250px",
                        cursor: "pointer"
                    }}
                    onClick={() => {
                        if (hideC) {
                            this.setState({
                                hideC: false
                            });
                        } else {
                            this.setState({
                                hideC: true
                            });
                        }
                    }}
                >
                    {hideC ? <ChevronRightIcon className="icon" /> : <ExpandMoreIcon className="icon" />}
                    <h5 className="m-0 d-inline">Entregue</h5>
                </div>
                <div className="p-3" style={{ margin: "0 0 0 250px", display: hideC ? "none" : "block" }}>
                    <Row>
                        {listPedidos.sort(function (a, b) {
                            return (a.numeroDoPedido > b.numeroDoPedido) ? 1 : ((b.numeroDoPedido > a.numeroDoPedido) ? -1 : 0);
                        }).map(pedido => {
                            if (pedido.estado == "Entregue") {
                                return (
                                    <Col lg={3}>
                                        <Card className="mb-3 shadow">
                                            <div className="w-100 bg-primary rounded-top" style={{ height: "50px" }}>
                                                <Image
                                                    src={require('./img/navi-logo-white.png')}
                                                    width="40"
                                                    height="40"
                                                    className="ml-2 mt-1"
                                                />
                                            </div>
                                            <Card.Body>
                                                <Card.Title className="text-primary">Pedido: {pedido.numeroDoPedido}</Card.Title>
                                                <Card.Subtitle className="mb-3 text-muted desc-pedido">
                                                    <i>{sessionStorage.getItem('@NAVI/tipo') == "Comprador" ? "Loja: " + pedido.loja.nome : "Comprador: " + pedido.comprador.nome}</i>
                                                </Card.Subtitle>
                                                <Card.Text>
                                                    <div className="mb-1 desc-pedido">
                                                        <b>Descrição: </b>
                                                        <span>{pedido.descricao}</span>
                                                    </div>
                                                    <div className="mb-1  desc-pedido">
                                                        <b>Entregador: </b>
                                                        <span>{pedido.entregador == null ? "Nenhum" : pedido.entregador.nome}</span>
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
                            }
                        })}
                    </Row>
                </div>
                <div
                    className="px-3 py-2 bg-white text-primary border-bottom"
                    style={{
                        margin: "0 0 0 250px",
                        cursor: "pointer"
                    }}
                    onClick={() => {
                        if (hideD) {
                            this.setState({
                                hideD: false
                            });
                        } else {
                            this.setState({
                                hideD: true
                            });
                        }
                    }}
                >
                    {hideD ? <ChevronRightIcon className="icon" /> : <ExpandMoreIcon className="icon" />}
                    <h5 className="m-0 d-inline">Cancelado</h5>
                </div>
                <div className="p-3" style={{ margin: "0 0 0 250px", display: hideD ? "none" : "block" }}>
                    <Row>
                        {listPedidos.sort(function (a, b) {
                            return (a.numeroDoPedido > b.numeroDoPedido) ? 1 : ((b.numeroDoPedido > a.numeroDoPedido) ? -1 : 0);
                        }).map(pedido => {
                            if (pedido.estado == "Cancelado") {
                                return (
                                    <Col lg={3}>
                                        <Card className="mb-3 shadow">
                                            <div className="w-100 bg-primary rounded-top" style={{ height: "50px" }}>
                                                <Image
                                                    src={require('./img/navi-logo-white.png')}
                                                    width="40"
                                                    height="40"
                                                    className="ml-2 mt-1"
                                                />
                                            </div>
                                            <Card.Body>
                                                <Card.Title className="text-primary">Pedido: {pedido.numeroDoPedido}</Card.Title>
                                                <Card.Subtitle className="mb-3 text-muted desc-pedido">
                                                    <i>{sessionStorage.getItem('@NAVI/tipo') == "Comprador" ? "Loja: " + pedido.loja.nome : "Comprador: " + pedido.comprador.nome}</i>
                                                </Card.Subtitle>
                                                <Card.Text>
                                                    <div className="mb-1 desc-pedido">
                                                        <b>Descrição: </b>
                                                        <span>{pedido.descricao}</span>
                                                    </div>
                                                    <div className="mb-1  desc-pedido">
                                                        <b>Entregador: </b>
                                                        <span>{pedido.entregador == null ? "Nenhum" : pedido.entregador.nome}</span>
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
                            }
                        })}
                    </Row>
                </div>

                {this.modalEdit(showModal, formDisabled, btnDanger, btnPrimary, onClickBtnPrimary, pedidoModal)}
            </div>
        );
    }
}