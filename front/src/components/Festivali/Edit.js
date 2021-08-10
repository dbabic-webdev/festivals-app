import React from 'react';
import FrontAxios from './../../apis/FrontAxios';
import {Form, Button, Table, Row, Col} from 'react-bootstrap'

class Edit extends React.Component{

    constructor(props) {
        super(props);
    
        let festival = {
            naziv: "",
            pocetak: "",
            kraj: "",
            cena: 0.00,
            brojKarata: 0,
            mesto: null
        };
    
        this.state = {
            festival: festival,
            mesta: []
        };
      }

      componentDidMount(){
        this.getData();
    }

    async getData() {
        await this.getFestival();
        await this.getMesta();
      }
  
      async doEdit() {
          try {
            await FrontAxios.put("/festivali/" + this.props.match.params.id, this.state.festival);
            this.props.history.push("/festivali");
          } catch (error) {
            alert("Nije uspelo čuvanje.");
          }
        }
  
  
        async getFestival() {
      
          try {
            let result = await FrontAxios.get("/festivali/" + this.props.match.params.id);
            if (result && result.status === 200) {
              this.setState({
                festival: result.data
              });
            }
          } catch (error) {
            alert("Nije uspelo dobavljanje.");
          }
        }
  
        async getMesta() {
          try {
            let result = await FrontAxios.get("/mesta");
            if (result && result.status === 200) {
              this.setState({
                mesta: result.data,
              });
            }
          } catch (error) {
            alert("Nije uspelo dobavljanje.");
          }
        }

        valueInputChange(event) {
            let control = event.target;
        
            let name = control.name;
            let value = control.value;
        
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

        render() {
            return (
              <div>
                <h1>Festival</h1>
        
                <Form>
                  <Form.Group>
                    <Form.Label>Naziv</Form.Label>
                    <Form.Control
                      onChange={(event) => this.valueInputChange(event)}
                      name="naziv"
                      value={this.state.festival.naziv}
                      as="input"
                    ></Form.Control>
                  </Form.Group>
                  <Form.Group>
                    <Form.Label>Datum pocetka festivala</Form.Label>
                    <Form.Control
                      onChange={(event) => this.valueInputChange(event)}
                      name="pocetak"
                      value={this.state.festival.pocetak}
                      type="date"
                      as="input"
                    ></Form.Control>
                  </Form.Group>
                  <Form.Group>
                    <Form.Label>Datum zavrsetka festivala</Form.Label>
                    <Form.Control
                      onChange={(event) => this.valueInputChange(event)}
                      name="kraj"
                      value={this.state.festival.kraj}
                      as="input"
                      type="date"
                    ></Form.Control>
                  </Form.Group>
                  <Form.Group>
                  <Form.Group>
                <Form.Label>Mesto odrzavanja festivala</Form.Label>
                <Form.Control
                  onChange={(event) => this.mestoSelectionChanged(event)}
                  name="mesto"
                  value={this.state.festival.mestoId}
                  as="select"
                >
                  <option value={this.state.festival.mestoId}></option>
                  {this.state.mesta.map((mesto) => {
                    return (
                      <option value={mesto.id} key={mesto.id}>
                        {mesto.grad}
                      </option>
                    );
                  })}
                </Form.Control>
              </Form.Group>
              </Form.Group>
                  <Form.Group>
                    <Form.Label>Cena karte</Form.Label>
                    <Form.Control
                      onChange={(event) => this.valueInputChange(event)}
                      name="cena"
                      value={this.state.festival.cena}
                      as="input"
                    ></Form.Control>
                  </Form.Group>
                  <Form.Group>
                    <Form.Label>Broj mesta</Form.Label>
                    <Form.Control
                      onChange={(event) => this.valueInputChange(event)}
                      name="brojKarata"
                      value={this.state.festival.brojKarata}
                      as="input"
                    ></Form.Control>
                  </Form.Group>
    
                  <Button variant="primary" onClick={() => this.doEdit()}>
                    Edit
                  </Button>
                </Form>
        
              </div>
            );
          }

}

export default Edit;