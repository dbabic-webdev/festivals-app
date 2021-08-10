import React from 'react';
import FrontAxios from './../../apis/FrontAxios';
import { Row, Col, Button, Form, ButtonGroup, Collapse } from "react-bootstrap";

class Add extends React.Component{

    constructor(props){
        super(props);

        let festival = {
            naziv: "",
            pocetak: "",
            kraj: "",
            cena: 0.00,
            brojKarata:0,
            mesto: null
        }

        this.state = {festival: festival, mesta: []};
    }

    componentDidMount(){
        this.getMesta();
    }

    async getMesta(){
        try{
            let result = await FrontAxios.get("/mesta");
            let mesta = result.data;
            this.setState({mesta: mesta});
            console.log("test1");
        }catch(error){
            console.log(error);
            alert("Couldn't fetch movies");
        }
    }

    async create(e){
        e.preventDefault();

        try{

            let festival = this.state.festival;
            let festivalDTO = {
                naziv: festival.naziv,
                pocetak: festival.pocetak,
                kraj: festival.kraj,
                cena: festival.cena,
                brojKarata: festival.brojKarata,
                mesto: festival.mesto
            }

            let response = await FrontAxios.post("/festivali", festivalDTO);
            this.props.history.push("/festivali");
        }catch(error){
            alert("Couldn't save the festival");
        }
    }

    valueInputChanged(e) {
        let input = e.target;
    
        let name = input.name;
        let value = input.value;
    
        let festival = this.state.festival;
        festival[name] = value;
    
        this.setState({ festival: festival });
      }

      mestoSelectionChanged(e){
        // console.log(e);

        let mestoId = e.target.value;
        let mesto = this.state.mesta.find((mesto) => mesto.id == mestoId);

        let festival = this.state.festival;
        festival.mesto = mesto;

        this.setState({festival: festival});
    }

    render(){
        return (
            <>
            {window.localStorage['role']=="ROLE_ADMIN"?
            [<Row>
                <Col></Col>
                <Col xs="12" sm="10" md="8">
                <h1>Dodaj liniju</h1>

                <Form>
                    <Form.Group>
                    <Form.Label htmlFor="pNaziv">Naziv festivala</Form.Label>
                    <Form.Control id="pNaziv" name="naziv" onChange={(e)=>this.valueInputChanged(e)}/> <br/>
                    </Form.Group>
                    
                    <Form.Group>
                    <Form.Label htmlFor="pPocetak">Datum pocetka festivala</Form.Label>
                    <Form.Control type="date" id="pPocetak" name="pocetak" onChange={(e)=>this.valueInputChanged(e)}/> <br/>
                    </Form.Group>

                    <Form.Group>
                    <Form.Label htmlFor="pKraj">Datum zavrsetka festivala</Form.Label>
                    <Form.Control type="date" id="pKraj" name="kraj" onChange={(e)=>this.valueInputChanged(e)}/> <br/>
                    </Form.Group>

                    <Form.Group>
                    <Form.Label id="pCena">Cena karte</Form.Label>
                    <Form.Control type="number" step=".01" id="pCena" name="cena" onChange={(e)=>this.valueInputChanged(e)}/> <br/>
                    </Form.Group>


                    <Form.Group>
                    <Form.Label id="pbrKarata">Broj dostupnih karata</Form.Label>
                    <Form.Control type="number" step=".01" id="pbrKarata" name="brojKarata" onChange={(e)=>this.valueInputChanged(e)}/> <br/>
                    </Form.Group>


                    <Form.Group>
                    <Form.Label htmlFor="pMesto">Mesto odrzavanja</Form.Label>
                    <Form.Control name="mesto" as="select" id="pMesto" onChange={event => this.mestoSelectionChanged(event)}>
                        <option></option>
                        {
                            this.state.mesta.map((mesto) => {
                                return (
                                    <option key={mesto.id} value={mesto.id}>{mesto.grad}, ({mesto.drzava})</option>
                                )
                            })
                        }
                    </Form.Control><br/>
                    </Form.Group>

                    <Button onClick={(event)=>{this.create(event);}}>Add</Button>
                </Form>
                </Col>
                <Col></Col>
            </Row>]: null}
            </>
        )
    }

}

export default Add;