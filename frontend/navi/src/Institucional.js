import React, { Component } from 'react';
import './css/index.css';

// Components
import NavbarInst from './NavbarInst';

// Bootstrap
import 'bootstrap/dist/css/bootstrap.css';
import {
    Row,
    Col,
    Image,
    Container
} from 'react-bootstrap';

class Institucional extends Component {
    render() {
        return (
            <div className="body">
                <NavbarInst />

                <Row className="justify-content-center m-0">
                    <Col sm="9">
                        <div className="painel shadow">
                            <Image
                                src={require('./img/navi-logo.png')}
                                width="200"
                                className="mb-3"
                            />
                            <h1>NAVI</h1>
                            <h5>Sempre monitorando sua entrega!</h5>
                        </div>
                    </Col>
                </Row>

                <div className="box">
                    <h3 id="sobre">Sobre Nós</h3>
                    <hr />
                    <Container>
                        <Row>
                            <Col>
                                <div id="card1" className={"card-panel"} >
                                    <p className="text-center mb-2">Quem Somos</p>
                                    <span>A NAVI é uma empresa que oferece é especialista no ramo de rastreamento de materiais para construção. Nossa grande missão, é facilitar o acompanhamento dos materiais para a obra dos clientes do começo até o fim, oferecendo a maior segurançã e qualidade no atendimento.</span>
                                </div>
                                <div className={"legend"} >
                                    <span>LER MAIS</span>
                                </div>
                            </Col>
                            <Col md="4">
                                <div id="card2" className={"card-panel"} >
                                    <p className="text-center mb-2">Trabalhe Conosco</p>
                                    <span>Se construir ou reformar faz parte dos seus sonhos, então seja bem-vindo. Somos uma startap inteligente que oferece a solução completa para monitorar a sua entregra.</span>
                                </div>
                                <div className={"legend"} >
                                    <span>LER MAIS</span>
                                </div>
                            </Col>
                        </Row>
                        <Row>
                            <Col md="4">
                                <div id="card3" className={"card-panel"} >
                                    <p className="text-center mb-2">Baixe o APP</p>
                                    <span>Se construir ou reformar faz parte dos seus sonhos, então seja bem-vindo. Somos uma startap inteligente que oferece a solução completa para monitorar a sua entregra.</span>
                                </div>
                                <div className={"legend"} >
                                    <span>LER MAIS</span>
                                </div>
                            </Col>
                            <Col md="8">
                                <div id="card4" className={"card-panel"} >
                                    <p className="text-center mb-2">Comunidade</p>
                                    <span>União, troca e compartilhamento. A NAVI lança, baseado nesses três pilares, a Comunidade para Clientes da marca. A proposta que pretende unir Colaboradores e Clientes nos espaços físico (Loja) e digital (plataforma), oferece a oportunidade de compartilhar ideias, esclarecer dúvidas e conhecer mais sobre os diversos assuntos que englobam o segmento.</span>
                                </div >
                                <div className={"legend"} >
                                    <span>LER MAIS</span>
                                </div>
                            </Col>
                        </Row>
                        <h3 className="contrate">CONTRATE A GENTE!!</h3>
                        <h4>Caso seja um vendedor de materias de contruções.</h4>
                    </Container>
                    <div className="caixa-azul">
                        <div className="fundo-azul"></div>
                        <div className="caixa-texto">
                            <Image
                                src={require('./img/navi-logo.png')}
                                width="50"
                                height="50"
                                alt="Navi"
                            />
                            <p>Caso deseje melhorar sua entrega para o cliente entre em contato com a gente atravez do nosso e-mail.</p>
                            <span className="caixa-email">EQUIPE_ATENDE@NAVI.COM</span>
                        </div>
                    </div>
                </div>

                <div className="bg-dark py-5">
                    <h3 id="parceiros" className="text-light mb-4">Pricipais Parceiras</h3>
                    <Container>
                        <Row>
                            <Col>
                                <Image
                                    src={require('./img/C&C_logo.jpeg')}
                                    width="200"
                                    height="100"
                                    className="my-1 logo-parceiras"
                                />
                            </Col>
                            <Col>
                                <Image
                                    src={require('./img/logo-dicico.png')}
                                    width="200"
                                    height="100"
                                    className="my-1 logo-parceiras"
                                />
                            </Col>
                            <Col>
                                <Image
                                    src={require('./img/logo-telhanorte.png')}
                                    width="200"
                                    height="100"
                                    className="my-1 logo-parceiras"
                                />
                            </Col>
                            <Col>
                                <Image
                                    src={require('./img/logo-merlin.png')}
                                    width="200"
                                    height="100"
                                    className="my-1 logo-parceiras"
                                />
                            </Col>
                        </Row>
                    </Container>
                </div>

                <div id="fim">
                    <span> NAVI © 2020</span>
                </div >
            </div>
        );
    }
}

export default Institucional;