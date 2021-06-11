'use strict'; // question 5

function _instanceof(left, right) { if (right != null && typeof Symbol !== "undefined" && right[Symbol.hasInstance]) { return right[Symbol.hasInstance](left); } else { return left instanceof right; } }

function _typeof(obj) { if (typeof Symbol === "function" && typeof Symbol.iterator === "symbol") { _typeof = function _typeof(obj) { return typeof obj; }; } else { _typeof = function _typeof(obj) { return obj && typeof Symbol === "function" && obj.constructor === Symbol && obj !== Symbol.prototype ? "symbol" : typeof obj; }; } return _typeof(obj); }

function _defineProperty(obj, key, value) { if (key in obj) { Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true }); } else { obj[key] = value; } return obj; }

function _classCallCheck(instance, Constructor) { if (!_instanceof(instance, Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } }

function _createClass(Constructor, protoProps, staticProps) { if (protoProps) _defineProperties(Constructor.prototype, protoProps); if (staticProps) _defineProperties(Constructor, staticProps); return Constructor; }

function _possibleConstructorReturn(self, call) { if (call && (_typeof(call) === "object" || typeof call === "function")) { return call; } return _assertThisInitialized(self); }

function _getPrototypeOf(o) { _getPrototypeOf = Object.setPrototypeOf ? Object.getPrototypeOf : function _getPrototypeOf(o) { return o.__proto__ || Object.getPrototypeOf(o); }; return _getPrototypeOf(o); }

function _assertThisInitialized(self) { if (self === void 0) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function"); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, writable: true, configurable: true } }); if (superClass) _setPrototypeOf(subClass, superClass); }

function _setPrototypeOf(o, p) { _setPrototypeOf = Object.setPrototypeOf || function _setPrototypeOf(o, p) { o.__proto__ = p; return o; }; return _setPrototypeOf(o, p); }

var RegistrationForm =
/*#__PURE__*/
function (_React$Component) {
  _inherits(RegistrationForm, _React$Component);

  function RegistrationForm(props) {
    var _this;

    _classCallCheck(this, RegistrationForm);

    _this = _possibleConstructorReturn(this, _getPrototypeOf(RegistrationForm).call(this, props));
    _this.state = {
      firstName: '',
      lastName: '',
      npi: '',
      address: '',
      telephone: '',
      email: ''
    };
    _this.handleChange = _this.handleChange.bind(_assertThisInitialized(_this));
    _this.handleSubmit = _this.handleSubmit.bind(_assertThisInitialized(_this));
    return _this;
  }

  _createClass(RegistrationForm, [{
    key: "handleChange",
    value: function handleChange(event) {
      this.setState(_defineProperty({}, event.target.name, event.target.value));
    }
  }, {
    key: "handleSubmit",
    value: function handleSubmit(event) {
      alert('Registration submitted: ' + JSON.stringify(this.state, null, 2));
      event.preventDefault();
    }
  }, {
    key: "render",
    value: function render() {
      return React.createElement("form", {
        onSubmit: this.handleSubmit
      }, React.createElement("fieldset", null, React.createElement("legend", null, "User Registration"), React.createElement("label", {
        for: "firstName"
      }, "First Name:"), React.createElement("input", {
        type: "text",
        name: "firstName",
        id: "firstName",
        value: this.state.firstName,
        onChange: this.handleChange
      }), React.createElement("label", {
        for: "lastName"
      }, "Last Name:"), React.createElement("input", {
        type: "text",
        name: "lastName",
        id: "lastName",
        value: this.state.lastName,
        onChange: this.handleChange
      }), React.createElement("label", {
        for: "npi"
      }, "NPI Number:"), React.createElement("input", {
        type: "text",
        name: "npi",
        id: "npi",
        value: this.state.npi,
        onChange: this.handleChange
      }), React.createElement("label", {
        for: "address"
      }, "Business Address:"), React.createElement("input", {
        type: "text",
        name: "address",
        id: "address",
        value: this.state.address,
        onChange: this.handleChange
      }), React.createElement("label", {
        for: "telephone"
      }, "Telephone Number:"), React.createElement("input", {
        type: "tel",
        name: "telephone",
        id: "telephone",
        value: this.state.telephone,
        onChange: this.handleChange
      }), React.createElement("label", {
        for: "email"
      }, "Email address:"), React.createElement("input", {
        type: "email",
        name: "email",
        id: "email",
        value: this.state.email,
        onChange: this.handleChange
      }), React.createElement("input", {
        type: "submit",
        value: "Submit"
      })));
    }
  }]);

  return RegistrationForm;
}(React.Component);

ReactDOM.render(React.createElement(RegistrationForm, null), document.querySelector("#registrationInterface"));