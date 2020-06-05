import React, { Component } from 'react';
import './css/Home.css';
import GoogleMapReact from 'google-map-react';

// Components
import Menu from './Menu';

// Bootstrap
import 'bootstrap/dist/css/bootstrap.css';
import {
    Image,
    Navbar,
    Nav,
    Row,
    Col
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

    componentDidMount() {
        if (sessionStorage.getItem('@NAVI/tipo') != "Comprador") {
            window.location = "/pedidos"
        }
    }

    render() {
        return (
            <div>
                <Menu />

                <div className="p-4 bg-white">
                    <Row>
                        <Col>
                            <h4 className="text-primary font-weight-light mb-0">Procurar Loja</h4>
                        </Col>
                    </Row>
                </div>
                <div>
                    <Row>
                        <Col>
                            <div className="p-3 my-3 rounded bg-white">
                                <Image
                                    src={require('./img/maps.png')}
                                    className="w-100"
                                    alt="Maps"
                                />
                            </div>
                        </Col>
                    </Row>
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