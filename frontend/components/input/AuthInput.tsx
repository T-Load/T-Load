import { AuthInputProps } from "constant/AuthInputProps";
import { Toast } from "primereact/toast";
import { useState, useRef, useEffect } from "react";

import "primereact/resources/themes/saga-blue/theme.css";
import "primereact/resources/primereact.min.css";

export default function AuthInput({
  label,
  name,
  type,
  placeholder,
  value,
  onChange,
  isConfirmPassword = false,
  originalPassword = "",
}: AuthInputProps) {
  const [error, setError] = useState("");
  const toast = useRef<Toast>(null);
  const [inputValue, setInputValue] = useState(value);

  useEffect(() => {
    if (error) {
      showToast("오류", error);
    }
  }, [error]);

  const showToast = (summary: string, detail: string) => {
    if (toast.current) {
      toast.current.clear();
      toast.current.show({
        severity: "error",
        summary,
        detail,
        life: 2500,
      });
    }
  };

  const validateInput = (value: string) => {
    if (type === "email") {
      const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      if (!emailRegex.test(value)) {
        const errorMessage = "유효한 이메일을 입력해주세요.";
        setError(errorMessage);
        showToast("오류", errorMessage);
        return;
      }
    }

    if (type === "password" && !isConfirmPassword) {
      if (value.length < 8) {
        const errorMessage = "비밀번호는 8자 이상 입력해주세요.";
        setError(errorMessage);
        showToast("오류", errorMessage);
        return;
      }
    }

    if (isConfirmPassword && originalPassword) {
      if (value !== originalPassword) {
        const errorMessage = "비밀번호가 일치하지 않습니다.";
        setError(errorMessage);
        showToast("오류", errorMessage);
        return;
      }
    }

    setError("");
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const newValue = e.target.value;
    setInputValue(newValue);
    onChange(newValue);
  };

  const handleBlur = (e: React.ChangeEvent<HTMLInputElement>) => {
    const newValue = e.target.value;
    validateInput(newValue);
  };

  return (
    <>
      <Toast ref={toast} position="top-center" />
      <div className="flex flex-col gap-2">
        <label htmlFor={name} className="text-[14px] font-poppins font-bold">
          {label}
        </label>
        <input
          id={name}
          name={name}
          type={type}
          placeholder={placeholder}
          value={inputValue}
          onChange={handleChange}
          onBlur={handleBlur}
          className="w-full px-4 py-3 border border-gray-400 rounded-md focus:outline-none focus:ring-0 focus:border-pink-400 focus:shadow-pink-400 focus:shadow-sm"
        />
        {error && <p className="text-red-500 text-sm">{error}</p>}
      </div>
    </>
  );
}
