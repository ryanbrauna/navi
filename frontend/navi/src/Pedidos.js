import React, { Component } from 'react';
// import './css/PedidosComprador.css';
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
            text: "Os dados do entregador foram atualizados.",
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