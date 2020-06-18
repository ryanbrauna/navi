import React, { Component } from 'react';
import './css/Home.css';
import GoogleMapReact from 'google-map-react';
import axios from 'axios';

// Components
import Menu from './Menu';

// Bootstrap
import 'bootstrap/dist/css/bootstrap.css';
import {
    Image,
    Navbar,
    Nav,
    Row,
    Col,
    CardColumns,
    Card
} from 'react-bootstrap';

const AnyReactComponent = ({ text }) => <div>{text}</div>;
export default class Home extends Component {
    static defaultProps = {
        center: {
            lat: 59.95,
            lng: 30.33
        },
        zoom: 11
    };

    constructor(props) {
        super(props);
        this.state = {
            listaLoja: []
        };
    }

    componentDidMount() {
        if (sessionStorage.getItem('@NAVI/tipo') != "Comprador") {
            window.location = "/pedidos"
        }
        axios.get("http://navi--api.herokuapp.com/lojas").then(data => {
            this.setState({ listaLoja: data.data });
            console.log(this.state.listaLoja);
        });
    }

    render() {
        const styleCard = {
            backgroundImage: `url(${require('./img/wp1.jpg')})`,
            backgroundSize: 'cover'
        };
        return (
            <div>
                <Menu />

                <div className="p-4 bg-white shadow-sm">
                    <Row>
                        <Col>
                            <h4 className="text-primary font-weight-light mb-0">Procurar Loja</h4>
                        </Col>
                    </Row>
                </div>
                <div>
                    {/* <Row>
                        <Col className="p-0">
                            <div className="rounded bg-white">
                                <Image
                                    src={require('./img/maps.png')}
                                    className="w-100"
                                    alt="Maps"
                                />
                            </div>
                        </Col>
                    </Row> */}
                    <div className="p-4" style={{ margin: "0 0 0 250px" }}>
                        <CardColumns>
                            {this.state.listaLoja.sort(function (a, b) {
                                return (a.nome > b.nome) ? 1 : ((b.nome > a.nome) ? -1 : 0);
                            }).map(loja => {
                                return (
                                    <Card style={styleCard} className="border shadow-sm">
                                        <Card.Body className="w-75 ml-auto bg-white">
                                            <Card.Title className="text-primary">{loja.nome}</Card.Title>
                                            <Card.Subtitle className="mb-2 text-muted">{loja.vendedor.telefone ? loja.vendedor.telefone : ""}</Card.Subtitle>
                                            <p>{loja.descricao}</p>
                                            <p>
                                                <i>{loja.endereco ? `${loja.endereco.logradouro}, ${loja.endereco.numero}, ${loja.endereco.bairro}, ${loja.endereco.localidade} - ${loja.endereco.uf}` : "Vendedor sem endereço"}</i>
                                            </p>
                                        </Card.Body>
                                        <Card.Footer className="bg-light">
                                            <small className="text-muted">{loja.vendedor.email}</small>
                                        </Card.Footer>
                                    </Card>
                                );
                            })}
                        </CardColumns>
                    </div>
                    {/* <div style={{ height: '100vh', width: '100%' }}>
                            <GoogleMapReact
                                bootstrapURLKeys={{ key: 'AIzaSyC1Ss07U7cpEDS_gqYwsw0amAGt3g-aD9c' }}
                                defaultCenter={this.props.center}
                                defaultZoom={this.props.zoom}
                            >
                                <AnyReactComponent
                                    lat={59.955413}
                                    lng={30.337844}
                                    text="My Marker"
                                />
                            </GoogleMapReact>
                        </div> */}
                </div>
            </div>
        );
    }
}