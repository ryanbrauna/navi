import React, { Component } from 'react';
import './css/Home.css';
import axios from 'axios';

//Maps
import { Map, InfoWindow, Marker, GoogleApiWrapper } from 'google-maps-react';
// Components
import Menu from './Menu';

// Bootstrap
import 'bootstrap/dist/css/bootstrap.css';
import {
    Row,
    Col,
    CardColumns,
    Card
} from 'react-bootstrap';

class Home extends Component {

    constructor(props) {
        super(props);

        this.state = {
            listaLoja: [],
            initialPosition: {
                latitude: 0,
                longitude: 0,
                latitudeDelta: 0,
                longitudeDelta: 0,
            }
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
        navigator.geolocation.getCurrentPosition((position) => {
            var lat = parseFloat(position.coords.latitude)
            var long = parseFloat(position.coords.longitude)

            var initialRegion = {
                latitude: lat,
                longitude: long
            }

            this.setState({ initialPosition: initialRegion })
        }, (error) => alert(JSON.stringify(error)), { enableHighAccuracy: true, timeout: 20000, maximumAge: 1000 });
    }

    render() {
        const containerStyle = {
            position: 'relative',
            width: '100%',
            height: '100%'
        }
        const styleCard = {
            backgroundImage: `url(${require('./img/wp1.jpg')})`,
            backgroundSize: 'cover'
        };

        return (
            <>
                <Menu />

                <div className="p-4 bg-white shadow-sm">
                    <Row>
                        <Col>
                            <h4 className="text-primary font-weight-light mb-0">Procurar Loja</h4>
                        </Col>
                    </Row>
                </div>
                <div style={{ margin: "0 0 0 250px", height: "450px" }}>
                    <Map
                        containerStyle={containerStyle}
                        // initialCenter={this.state.initialPosition}
                        google={this.props.google} zoom={16}
                    >

                        <Marker onClick={this.onMarkerClick}
                            name={'Current location'} />

                        <InfoWindow onClose={this.onInfoWindowClose}>
                        </InfoWindow>
                    </Map>
                </div>
                <div className="p-4" style={{ margin: "0 0 0 250px" }}>
                    <CardColumns>
                        {this.state.listaLoja.sort(function (a, b) {
                            return (a.nome > b.nome) ? 1 : ((b.nome > a.nome) ? -1 : 0);
                        }).map(loja => {
                            return (
                                <>
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
                                </>
                            );
                        })}
                    </CardColumns>
                </div>
            </>
        );
    }
}

export default GoogleApiWrapper({
    apiKey: ("AIzaSyC1Ss07U7cpEDS_gqYwsw0amAGt3g-aD9c")
})(Home)