import React, { Component } from 'react';
import './css/Home.css';
import GoogleMapReact from 'google-map-react';

// Components
import Menu from './Menu';

// Bootstrap
import 'bootstrap/dist/css/bootstrap.css';

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
        if(sessionStorage.getItem('@NAVI/tipo') != "Comprador"){
            window.location = "/pedidos"
        }
    }

    render() {
        return (
            <div>
                <Menu />

                <div className="d-flex p-5">
                    <div className="rounded bg-white p-2">
                        <div style={{ height: '100vh', width: '100%' }}>
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
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}