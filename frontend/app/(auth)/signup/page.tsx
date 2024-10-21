"use client";
import AuthInput from "@components/input/AuthInput";
import { AuthInputProps } from "constant/AuthInputProps";
import Image from "next/image";
import Link from "next/link";
import { useState } from "react";

export default function SignUpForm() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  const emailProps: AuthInputProps = {
    label: "Email",
    name: "email",
    type: "email",
    placeholder: "이메일을 입력하세요",
    value: email,
    onChange: setEmail,
  };

  const passwordProps: AuthInputProps = {
    label: "Password",
    name: "password",
    type: "password",
    placeholder: "비밀번호를 입력하세요",
    value: password,
    onChange: setPassword,
  };

  const passwordConfirmProps: AuthInputProps = {
    label: "Confirm Password",
    name: "confirmPassword",
    type: "password",
    placeholder: "비밀번호를 확인하세요",
    value: confirmPassword,
    onChange: setConfirmPassword,
    isConfirmPassword: true,
    originalPassword: password,
  };

  const handleSignIn = (e: React.FormEvent) => {
    e.preventDefault();
    if (password !== confirmPassword) {
      alert("비밀번호가 일치하지 않습니다.");
      return;
    }
    console.log("이메일:", email);
    console.log("비밀번호:", password);
  };

  return (
    <div className="flex flex-col items-center justify-center h-screen">
      <form
        onSubmit={handleSignIn}
        className="flex flex-col w-[300px] h-fit gap-6 px-6 py-3 border bg-white border-gray-300 rounded-xl shadow-md pt-8">
        <Link href="/" className="flex justify-center mb-3">
          <Image
            src="/image/Main_Logo.webp"
            width={220}
            height={220}
            alt="아파모아"
            className="border-4 p-4 rounded-xl border-green-600"
          />
        </Link>
        <AuthInput {...emailProps} />
        <AuthInput {...passwordProps} />
        <AuthInput {...passwordConfirmProps} />
        <button
          type="submit"
          className="bg-green-500 text-white flex justify-center py-5 mt-2 mb-2 px-4 rounded-md hover:bg-green-700 transition-colors">
          회원가입
        </button>
        <div className="flex gap-3 justify-center mb-4">
          <span className="font-poppins text-xl">계정이 이미 있으신가요?</span>
          <Link href="/signin">
            <p className="text-xl font-roboto font-bold">Trip to signIn</p>
          </Link>
        </div>
      </form>
    </div>
  );
}
