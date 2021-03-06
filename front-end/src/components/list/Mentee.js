import React from "react";
import ListGroupItem from "react-bootstrap/ListGroupItem";
import Button from "react-bootstrap/Button";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import { api } from "./../../util/api";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTimes } from "@fortawesome/free-solid-svg-icons";
import "./Mentee.css";
import { handleResponse, mentorRemovedMessage } from "../../util/notify";

export default function Mentee({mentor, mentee, refreshCraftspeople, idToken }) {
  function removeMentee() {
    api({
      endpoint: `/craftspeople/mentee/remove`,
      token: idToken,
      type: "PUT",
      body: {
        menteeId: mentee.id,
        mentorId: mentor.id
      }
    }).then(response => {
      handleResponse(
        response,
        mentorRemovedMessage(mentee.firstName),
        refreshCraftspeople
      );
    });
  }

  return (
    <ListGroupItem data-testid={`mentee-${mentee.id}`}>
      <Container>
        <Row>
          <Col sm={1}>
            <Button
              className="remove-button"
              variant="link"
              data-testid="removementeebutton"
              onClick={() => removeMentee()}
            >
              <FontAwesomeIcon
                className="times-icon"
                icon={faTimes}
                size="lg"
              />
            </Button>
          </Col>
          <Col sm={11}>
            <h5 className="mentee-name">
              {mentee.firstName} {mentee.lastName}
            </h5>
          </Col>
        </Row>
      </Container>
    </ListGroupItem>
  );
}
