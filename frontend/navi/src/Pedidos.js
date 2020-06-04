import React, { Component } from 'react';
import { Link } from 'react-router-dom';
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
import RoomIcon from '@material-ui/icons/Room';
import AddIcon from '@material-ui/icons/Add';
import CloseIcon from '@material-ui/icons/Close';

export default class Home extends Component {

    constructor(props) {
        super(props);
        this.state = this.initialState;
    }

    salvarPedido = () => {
        swal({
            title: "Sucesso!",
            text: "Os dados do Pedido foram atualizados.",
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
            btnPrimary: "Alterar Cadastro",
            onClickBtnPrimary: this.editarPedido
        });
    }

    initialState = {
        showModal: false,
        formDisabled: true,
        btnDanger: "Excluir Pedido",
        btnPrimary: "Alterar Cadastro",
        onClickBtnPrimary: this.editarPedido,
        hideFormCad: "d-none",
        nomePedido: "",
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

    render() {
        const { showModal, formDisabled, btnDanger, btnPrimary, onClickBtnPrimary, hideFormCad } = this.state;

        return (
            <div>
                <Menu />

                <div className="p-4 bg-white">
                    <Row>
                        <Col>
                            <h4 className="text-primary mb-4 font-weight-light">Pedidos</h4>
                        </Col>
                    </Row>
                    <Row>
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
                                <Row>
                                    <Col>
                                        <Form.Group>
                                            <Form.Label className="mb-0"><span className="text-danger">*</span> Nome do Pedido:</Form.Label>
                                            <Form.Control
                                                type="text"
                                                placeholder="Nome do Pedido"
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

                <div className="p-4 d-flex">
                    <Card style={{ width: '18rem' }}>
                        <Card.Img variant="top" src={require('./img/caminhao-de-costa.jpg')} />
                        <Card.Body>
                            <Card.Title className="text-primary">Revestimento</Card.Title>
                            <Card.Text>
                                <span>Joli Material de Construção</span>
                                <br/>
                                <span className="text-muted">(11) 99876-5432</span>
                            </Card.Text>
                        </Card.Body>
                        <Card.Footer >
                            <Link to="/home">
                                <RoomIcon className="icon" />
                                <span>Acompanhar entrega</span>
                            </Link>
                        </Card.Footer>
                    </Card>
                </div>
            </div>
        );
    }
}