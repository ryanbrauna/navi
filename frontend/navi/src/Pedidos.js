import React, { Component } from 'react';
// import './css/PedidosComprador.css';
import { Link } from 'react-router-dom';

// Components
import Menu from './Menu';

// Bootstrap
import 'bootstrap/dist/css/bootstrap.css';
import {
    Card
} from 'react-bootstrap';

//Icon
import RoomIcon from '@material-ui/icons/Room';

export default class Home extends Component {
    render() {
        return (
            <div>
                <Menu />

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