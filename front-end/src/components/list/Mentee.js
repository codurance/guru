import React from "react";
import ListGroupItem from "react-bootstrap/ListGroupItem";
import Button from "react-bootstrap/Button";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import { api } from "./../../util/api";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTimes } from "@fortawesome/free-solid-svg-icons";
import "./Mentee.css"

export default function Mentee({ mentee, rerender }) {
  function removeMentee() {
    api({
      endpoint: `/craftspeople/mentee/remove/${mentee.id}`,
      type: "PUT",
    });
    rerender();
  }

  return (
    <ListGroupItem data-testid={`mentee-${mentee.id}`}>
      <Container>
        <Row>
          <Col />
          <Col>
            <h4 className="mentee-name menteeName">
              {mentee.firstName} {mentee.lastName}
            </h4>
          </Col>
          <Col>
            <Button
              className="remove-button"
              variant="danger"
              data-testid="removementeebutton"
              onClick={() => removeMentee()}
            >
              <FontAwesomeIcon className="times-icon" icon={faTimes} size="sm" />
            </Button>
          </Col>
        </Row>
      </Container>
    </ListGroupItem>
  );
}
