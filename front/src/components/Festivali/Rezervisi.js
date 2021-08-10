import React from "react";
import { Form, Button } from "react-bootstrap";
import FrontAxios from "../../apis/FrontAxios";

class Reservation extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      festivalNaziv: "",
      festivalId: -1,
      brojKarata: 0,
      cenaKarte: 0,
    };
  }

  componentDidMount() {
    this.getFestById(this.props.match.params.id);
  }

  getFestById(festId) {
    FrontAxios.get("/festivali/" + festId)
      .then((res) => {
        // handle success
        console.log(res);
        this.setState({
          festivalId: res.data.id,
          festivalNaziv: res.data.naziv,
          cenaKarte: res.data.cena,
        });
      })
      .catch((error) => {
        // handle error
        console.log(error);
        alert("Error occured please try again!");
      });
  }

  brojKarata = (event) => {
    console.log(event.target.value);

    const { name, value } = event.target;
    console.log(name + ", " + value);

    this.setState((state, props) => ({
      brojKarata: value,
    }));
  };

  reserve() {
    var params = {
      festivalId: this.state.festivalId,
      brojKarata: this.state.brojKarata,
    };

    FrontAxios.post("/rezervacije/", params)
      .then((res) => {
        // handle success
        console.log(res);
        alert("Reservation was added successfully!");
        this.props.history.push("/festivali");
      })
      .catch((error) => {
        // handle error
        console.log(error);
        alert("Error occured please try again!");
      });
  }

  render() {
    return (
      <div>
        <h1>Rezervisi kartu za: {this.state.festivalNaziv}</h1>
        <Form>
          <Form.Group>
            <label htmlFor="brojKarata">Broj karata</label>
            <input
              id="brojKarata"
              type="number"
              value={this.state.brojKarata}
              onChange={(e) => this.brojKarata(e)}
            />
            <br />
          </Form.Group>
          <Button onClick={() => this.reserve()}>Rezervisi</Button>
        </Form>
      </div>
    );
  }
}

export default Reservation;
