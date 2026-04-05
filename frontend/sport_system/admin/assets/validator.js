export function Validator(options) {

  function getParent(element, selector) {
    while(element.parentElement) {
      if(element.parentElement.matches(selector)) {
        return element.parentElement;
      }
      element = element.parentElement;
    }
  }
  var selectorRules = {};

  //hàm thực hiện validate
  function validate(inputElement, rule) {
    var errorMassage;
    var errorElement =
      getParent(inputElement, options.formGroupSelector).querySelector(".form-messenger");
    //lấy ra các rules của selector
    var rules = selectorRules[rule.selector];

    //lập qua từng rule và kiểm tra
    //nếu lỗi thì dừng việc kiểm tra
    for (var i = 0; i < rules.length; i++) {
      switch (inputElement.type) {
        case 'radio':
        case 'checkbox':
          errorMassage = rules[i](
            formElement.querySelector(rule.selector + ':checked')
          );
        case 'file':
          errorMassage = rules[i](inputElement.files);
          break;
        default:
          errorMassage = rules[i](inputElement.value.trim());
      }
      if (errorMassage) break;
    }
    if (errorMassage) {
      errorElement.innerText = errorMassage;
      getParent(inputElement, options.formGroupSelector).classList.add("invalid");
    } else {
      errorElement.innerText = "";
      getParent(inputElement, options.formGroupSelector).classList.add("invalid");
    }
    return !errorMassage;
  }

  //lấy element của form cần validate
  var formElement = document.querySelector(options.form);
  if (formElement) {
    //form khi submit
    formElement.onsubmit = function (e) {
      e.preventDefault();

      var isFormValid = true;

      //thực hiện lặp qua từng rule và validate
      options.rules.forEach(function (rule) {
        var inputElement = formElement.querySelector(rule.selector);
        var isVarlid = validate(inputElement, rule);
        if (!isVarlid) {
          isFormValid = false;
        }
      });

      if (isFormValid) {
        if (typeof options.onSubmit === 'function') {
          var enableInputs = formElement.querySelectorAll('[name]');
          var formValues = Array.from(enableInputs).reduce(function (
            values,
            input
          ) {
            switch(input.type) {
              case 'radio':
                values[input.name] = formElement.querySelector('input[name="' + input.name +'"]:checked').value;
                break;
              case 'checkbox':
                if(input.matches(':checked')) return values;
                if(!Array.isArray(values[input.name])) {
                  values[input.name] = [];
                }
                values[input.name].push(input.value);
              default:
                values[input.name] = input.value;
            }
            return values;
          }, {});
          options.onSubmit(formValues);
        } else {
          formElement.submit();
        }
      }
    };
    options.rules.forEach(function (rule) {
      //lưu lại các rulues trong mỗi input
      if (Array.isArray(selectorRules[rule.selector])) {
        selectorRules[rule.selector].push(rule.test);
      } else {
        selectorRules[rule.selector] = [rule.test];
      }
      var inputElements = formElement.querySelectorAll(rule.selector);

      Array.from(inputElements).forEach(function(inputElement) {
        //xử lý trường hợp blur khỏi input
        inputElement.onblur = function () {
          validate(inputElement, rule);
        };

        //xử lý mỗi khi người dùng nhập vào input
        inputElement.oninput = function () {
          var errorElement =
            getParent(inputElement, options.formGroupSelector).querySelector(".form-messenger");
          errorElement.innerText = "";
          inputElement.parentElement.classList.remove("invalid");
        };
      })
    });
  }
}
//nguyên tắc của rules
//1.khi có lỗi => trả ra massage lỗi
//2.không lỗi => thì thôi
Validator.isRequired = function (selector) {
  return {
    selector: selector,
    test: function (value) {
      return value.trim() !== "" ? undefined : "Vui lòng nhập dữ liệu";
    },
  };
};
Validator.isEmail = function (selector) {
  return {
    selector: selector,
    test: function (value) {
      var regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
      return regex.test(value) ? undefined : "Nhập đúng định dạng email";
    },
  };
};

Validator.isConfirmed = function (selector, getConfirmValue, message) {
  return {
    selector: selector,
    test: function (value) {
      return value === getConfirmValue()
        ? undefined
        : message || "Giá trị không khớp";
    },
  };
};
Validator.isRequiredFile = function (selector) {
  return {
    selector: selector,
    test: function (files) {
      return files && files.length > 0
        ? undefined
        : "Vui lòng chọn ảnh";
    },
  };
};
Validator.isRequiredEditor = function (selector, getEditorData) {
  return {
    selector: selector,
    test: function () {
      var value = getEditorData()
        .replace(/<[^>]*>/g, '') // bỏ HTML
        .trim();
      return value ? undefined : "Vui lòng nhập nội dung";
    },
  };
};


