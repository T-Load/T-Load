export interface AuthInputProps {
  label: string;
  name: string;
  type: "email" | "password" | "text";
  placeholder: string;
  value: string;
  onChange: (value: string) => void;
}
