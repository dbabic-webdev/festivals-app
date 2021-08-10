import React from 'react';
import FrontAxios from './../../apis/FrontAxios';
import {Form, Button, Table, Collapse, ButtonGroup} from 'react-bootstrap'

class Festivali extends React.Component{

    constructor(props) {
        super(props);

        this.state = {
            festivali: [],
            mesta: [],
            search: { naziv: "", mestoId: -1 },
            pageNo: 0,
            totalPages: 0
        }
    }

    componentDidMount(){
        this.getFestivali(0);
        this.getMesta();
    }

    getMesta(){
        FrontAxios.get('/mesta')
            .then(res => {
                console.log(res);
                this.setState({mesta: res.data});
            })
            .catch(error => {
                // handle error
                console.log(error);
                alert('Error occured please try again!');
            });
    }

    
    getFestivali(pageNo) {
        let config ={
            params: {
                pageNo: pageNo
            }
        }
        if(this.state.search.naziv!=""){
            config.params.naziv=this.state.search.naziv
        }
        if(this.state.search.mestoId!=-1){
            config.params.mestoId=this.state.search.mestoId
        }

        FrontAxios.get('/festivali',config)
            .then(res => {
                 // handle success
                 console.log(res);
                 this.setState({
                    festivali: res.data,
                    totalPages: res.headers['total-pages'],
                    pageNo: pageNo
                });
            })
            .catch(error => {
                // handle error
                console.log(error);
                alert('Error occured please try again!');
            });
    }

    delete(festId) {
        FrontAxios.delete('/festivali/' + festId)
        .then(res => {
            // handle success
            console.log(res);
            alert('Festival was deleted successfully!');
            window.location.reload();
        })
        .catch(error => {
            // handle error
            console.log(error);
            alert('Error occured please try again!');
         });
    }

    goToAdd() {
        this.props.history.push('/festivali/add');  
    }

    goToEdit(festid) {
      this.props.history.push('/festivali/edit/'+ festid); 
  }

  goToReserve(festId) {
    this.props.history.push('/festivali/rezervisi/'+ festId); 
}

searchValueInputChange(event) {
    let control = event.target;

    let name = control.name;
    let value = control.value;

    let search = this.state.search;
    search[name] = value;

    this.setState({ search: search });
    this.getFestivali(0);
  }

  renderFestivali() {
    return this.state.festivali.map((fest, index) => {
        return (
           <tr key={fest.id}>
              <td>{fest.naziv}</td>
              <td>{fest.mesto.grad}, ({fest.mesto.drzava})</td>
              <td>{fest.pocetak}</td>
              <td>{fest.kraj}</td>
              <td>{fest.cena}</td>
              <td>{fest.brojKarata}</td>
              {window.localStorage['role']=='ROLE_ADMIN'?
              [<td> <Button variant="warning" onClick={() => this.goToEdit(fest.id)}>Edit</Button> </td>,
              <td> <Button variant="danger" onClick={() => this.delete(fest.id)}>Delete</Button> </td>]:
              null}
              {window.localStorage['role']=='ROLE_KORISNIK'?
               [<td><Button variant="success" disabled={fest.brojMesta==0} onClick={() => this.goToReserve(fest.id)}>Rezervisi</Button></td>]: null}
           </tr>
        )
     })
}

render(){
    return(
        <div>
            <h1>Festivali</h1>
            <Form.Group style={{marginTop:35}}>
      <Form.Check type="checkbox" label="Show search form" onClick={(event) => this.setState({showSearch: event.target.checked})}/>
    </Form.Group>
    <Collapse in={this.state.showSearch}>
    <Form style={{marginTop:10}}>
      <Form.Group>
        <Form.Label>Naziv</Form.Label>
        <Form.Control
          value={this.state.search.naziv}
          name="naziv"
          as="input"
          onChange={(e) => this.searchValueInputChange(e)}
        ></Form.Control>
      </Form.Group>
      <Form.Group>
        <Form.Label>Mesto</Form.Label>
        <Form.Control
          onChange={(event) => this.searchValueInputChange(event)}
          name="mestoId"
          value={this.state.search.mestoId}
          as="select"
        >
          <option value={-1}></option>
          {this.state.mesta.map((mesto) => {
            return (
              <option value={mesto.id} key={mesto.id}>
                {mesto.grad}
              </option>
            );
          })}
        </Form.Control>
      </Form.Group>
          <br></br>
    </Form>
    </Collapse>
    <ButtonGroup style={{ marginTop: 25, float:"right"}}>
      <Button 
        style={{ margin: 3, width: 90}}
        disabled={this.state.pageNo==0} onClick={()=>this.getFestivali(this.state.pageNo-1)}>
        Previous
      </Button>
      <Button
        style={{ margin: 3, width: 90}}
        disabled={this.state.pageNo==this.state.totalPages-1} onClick={()=>this.getFestivali(this.state.pageNo+1)}>
        Next
      </Button>
    </ButtonGroup>
    {window.localStorage['role']=='ROLE_ADMIN'?
    [<Button onClick={() => this.goToAdd() }>Dodavanje</Button>]: null}
          <Table bordered striped style={{ marginTop: 5 }}>
            <thead  className="thead-dark">
                <tr>
                    <th>Naziv festivala</th>
                    <th>Mesto odrzavanja</th>
                    <th>Datum pocetka</th>
                    <th>Datum zavrsetka</th>
                    <th>Cena karte (RSD)</th>
                    <th>Broj preostalih mesta</th>
                    {window.localStorage['role']=='ROLE_KORISNIK'?
                    [<th></th>]: null}
                    {window.localStorage['role']=='ROLE_ADMIN'?
                    [<th></th>,
                    <th></th>]: null}
                </tr>
            </thead>
            <tbody>
                {this.renderFestivali()}
            </tbody>   
          </Table>
        </div>
    )
}


}

export default Festivali;